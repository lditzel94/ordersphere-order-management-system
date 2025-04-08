package dev.luciano.ordersphere.domain.entity

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import dev.luciano.ordersphere.domain.error.OrderDomainError
import dev.luciano.ordersphere.domain.error.OrderError
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
import java.util.UUID

sealed class Order(
    open val orderId: OrderId,
    val price: Money,
    val items: List<OrderItem>
) : AggregateRoot<OrderId>(orderId) {
    abstract val customerId: CustomerId
    abstract val restaurantId: RestaurantId
    abstract val deliveryAddress: StreetAddress
    abstract val trackingId: TrackingId
    val orderState: OrderState
        get() = when (this) {
            is Pending -> PENDING
            is Paid -> PAID
            is Approved -> APPROVED
            is Cancelling -> CANCELLING
            is Cancelled -> CANCELLED
        }

    init {
        require(price.isGreaterThanZero) { "Order price must be greater than zero" }
        require(items.sumOf { it.subTotal.amount } == price.amount) {
            "The sum of item subtotals does not match the order price=${price.amount}"
        }
    }

    companion object {
        fun from(
            orderState: OrderState,
            orderId: OrderId,
            customerId: CustomerId,
            restaurantId: RestaurantId,
            trackingId: TrackingId,
            orderPrice: Money,
            deliveryAddress: StreetAddress,
            orderItems: List<OrderItem>,
        ): Order = when (orderState) {
            PENDING -> Pending(
                orderId = orderId,
                customerId = customerId,
                restaurantId = restaurantId,
                trackingId = trackingId,
                orderPrice = orderPrice,
                deliveryAddress = deliveryAddress,
                orderItems = orderItems,
            )

            PAID -> Paid(
                orderId = orderId,
                customerId = customerId,
                restaurantId = restaurantId,
                trackingId = trackingId,
                orderPrice = orderPrice,
                deliveryAddress = deliveryAddress,
                orderItems = orderItems,
            )

            APPROVED -> Approved(
                orderId = orderId,
                customerId = customerId,
                restaurantId = restaurantId,
                trackingId = trackingId,
                orderPrice = orderPrice,
                deliveryAddress = deliveryAddress,
                orderItems = orderItems,
            )

            CANCELLING -> Cancelling(
                orderId = orderId,
                customerId = customerId,
                restaurantId = restaurantId,
                trackingId = trackingId,
                orderPrice = orderPrice,
                deliveryAddress = deliveryAddress,
                orderItems = orderItems,
            )

            CANCELLED -> Cancelled(
                orderId = orderId,
                customerId = customerId,
                restaurantId = restaurantId,
                trackingId = trackingId,
                orderPrice = orderPrice,
                deliveryAddress = deliveryAddress,
                orderItems = orderItems,
            )
        }
    }

    data class Pending(
        override val orderId: OrderId = OrderId(UUID.randomUUID()),
        override val customerId: CustomerId,
        override val restaurantId: RestaurantId,
        override val deliveryAddress: StreetAddress,
        private val orderPrice: Money,
        private val orderItems: List<OrderItem>,
        override val trackingId: TrackingId = TrackingId(UUID.randomUUID()),
    ) : Order(orderId = orderId, price = orderPrice, items = orderItems)

    data class Paid(
        override val orderId: OrderId,
        override val customerId: CustomerId,
        override val restaurantId: RestaurantId,
        override val deliveryAddress: StreetAddress,
        private val orderPrice: Money,
        private val orderItems: List<OrderItem>,
        override val trackingId: TrackingId,
    ) : Order(orderId = orderId, price = orderPrice, items = orderItems)

    data class Approved(
        override val orderId: OrderId,
        override val customerId: CustomerId,
        override val restaurantId: RestaurantId,
        override val deliveryAddress: StreetAddress,
        private val orderPrice: Money,
        private val orderItems: List<OrderItem>,
        override val trackingId: TrackingId,
    ) : Order(orderId = orderId, price = orderPrice, items = orderItems)

    data class Cancelling(
        override val orderId: OrderId,
        override val customerId: CustomerId,
        override val restaurantId: RestaurantId,
        override val deliveryAddress: StreetAddress,
        private val orderPrice: Money,
        private val orderItems: List<OrderItem>,
        override val trackingId: TrackingId,
    ) : Order(orderId = orderId, price = orderPrice, items = orderItems)

    data class Cancelled(
        override val orderId: OrderId,
        override val customerId: CustomerId,
        override val restaurantId: RestaurantId,
        override val deliveryAddress: StreetAddress,
        private val orderPrice: Money,
        private val orderItems: List<OrderItem>,
        override val trackingId: TrackingId,
    ) : Order(orderId = orderId, price = orderPrice, items = orderItems)
}

fun Order.pay(): Either<OrderError, Order.Paid> = either {
    Order.Paid(
        orderId = orderId,
        customerId = customerId,
        restaurantId = restaurantId,
        deliveryAddress = deliveryAddress,
        orderPrice = price,
        orderItems = items,
        trackingId = trackingId,
    ).also {
        ensure(orderState == PENDING) {
            OrderDomainError("Order with id=$orderId is not in pending state")
        }
    }
}

fun Order.approve(): Either<OrderError, Order.Approved> = either {
    Order.Approved(
        orderId = orderId,
        customerId = customerId,
        restaurantId = restaurantId,
        deliveryAddress = deliveryAddress,
        orderPrice = price,
        orderItems = items,
        trackingId = trackingId,
    ).also {
        ensure(orderState == PAID) {
            OrderDomainError("Order with id=$orderId is not in paid state")
        }
    }
}

fun Order.initCancel(): Either<OrderError, Order.Cancelling> = either {
    Order.Cancelling(
        orderId = orderId,
        customerId = customerId,
        restaurantId = restaurantId,
        deliveryAddress = deliveryAddress,
        orderPrice = price,
        orderItems = items,
        trackingId = trackingId,
    ).also {
        ensure(orderState == PAID) {
            OrderDomainError("Order with id=$orderId cannot be cancelled in paid state")
        }
    }
}

fun Order.cancel(): Either<OrderError, Order.Cancelled> = either {
    Order.Cancelled(
        orderId = orderId,
        customerId = customerId,
        restaurantId = restaurantId,
        deliveryAddress = deliveryAddress,
        orderPrice = price,
        orderItems = items,
        trackingId = trackingId,
    ).also {
        ensure(orderState == CANCELLING || orderState == PENDING) {
            OrderDomainError("Order with id=$orderId cannot be cancelled in $orderState state")
        }
    }
}
