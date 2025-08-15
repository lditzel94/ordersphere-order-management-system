package dev.luciano.ordersphere.domain.service

import arrow.core.Either
import arrow.core.raise.either
import dev.luciano.ordersphere.configuration.logger.CompanionLogger
import dev.luciano.ordersphere.domain.entity.order.Order
import dev.luciano.ordersphere.domain.error.OrderError
import dev.luciano.ordersphere.domain.event.OrderPaidEvent

fun interface OrderPaymentService {
    operator fun invoke(order: Order): Either<OrderError, OrderPaidEvent>
}

class OrderPaymentDomainService : OrderPaymentService {
    companion object : CompanionLogger()

    override fun invoke(order: Order): Either<OrderError, OrderPaidEvent> = either {
        // TODO("Refactor")
        OrderPaidEvent(orderId = order.orderId.value)
            .log { info("Order with id: {} has been paid", order.orderId) }
    }
}