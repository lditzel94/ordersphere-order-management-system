package dev.luciano.ordersphere.domain.entity.order

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import dev.luciano.ordersphere.domain.error.OrderDomainError
import dev.luciano.ordersphere.domain.error.OrderError
import dev.luciano.ordersphere.domain.factory.OrderFactory
import dev.luciano.ordersphere.domain.valueobject.CustomerId
import dev.luciano.ordersphere.domain.valueobject.Money
import dev.luciano.ordersphere.domain.valueobject.OrderId
import dev.luciano.ordersphere.domain.valueobject.OrderState
import dev.luciano.ordersphere.domain.valueobject.OrderState.APPROVED
import dev.luciano.ordersphere.domain.valueobject.OrderState.CANCELLED
import dev.luciano.ordersphere.domain.valueobject.OrderState.CANCELLING
import dev.luciano.ordersphere.domain.valueobject.OrderState.PAID
import dev.luciano.ordersphere.domain.valueobject.OrderState.PENDING
import dev.luciano.ordersphere.domain.valueobject.RestaurantId
import dev.luciano.ordersphere.domain.valueobject.StreetAddress
import dev.luciano.ordersphere.domain.valueobject.TrackingId

sealed interface Order {
    val orderId: OrderId
    val state: OrderState
    val price: Money
    val customerId: CustomerId

    companion object {
        private val factory = OrderFactory()

        fun create(init: OrderFactory.() -> Order): Order = factory.init()
    }
}

fun Order.pay(): Either<OrderError, Order> = either {
    when (this@pay) {
        is ClosedOrder -> raise(OrderDomainError("Order with id=$orderId cannot be paid in $state state"))

        is OpenOrder -> copy(state = PAID).also {
            ensure(state == PENDING) {
                OrderDomainError("Order with id=$orderId is not in pending state")
            }
        }
    }
}

fun Order.approve(): Either<OrderError, Order> = either {
    when (this@approve) {
        is ClosedOrder -> raise(OrderDomainError("Order with id=$orderId cannot be approved in $state state"))

        is OpenOrder -> copy(state = APPROVED).also {
            ensure(state == PAID) {
                OrderDomainError("Order with id=$orderId is not in paid state")
            }
        }
    }
}

fun Order.initCancel(cancellationReason: String): Either<OrderError, ClosedOrder> = either {
    when (this@initCancel) {
        is ClosedOrder -> raise(OrderDomainError("Order with id=$orderId cannot be cancelled in $state state"))

        is OpenOrder -> ClosedOrder(
            orderId = orderId,
            state = CANCELLING,
            price = price,
            customerId = customerId,
            cancellationReason = cancellationReason,
        )
    }
}

fun Order.cancel(): Either<OrderError, ClosedOrder> = either {
    when (this@cancel) {
        is ClosedOrder -> copy(state = CANCELLED)

        is OpenOrder -> raise(OrderDomainError("Order with id=$orderId cannot be cancelled without request init cancel"))
    }
}