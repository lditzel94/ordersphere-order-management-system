package dev.luciano.ordersphere.domain.service

import arrow.core.Either
import arrow.core.raise.either
import dev.luciano.ordersphere.configuration.logger.CompanionLogger
import dev.luciano.ordersphere.domain.entity.order.Order
import dev.luciano.ordersphere.domain.error.OrderError
import dev.luciano.ordersphere.domain.event.OrderCancelledEvent

fun interface OrderPaymentCancellationService {
    operator fun invoke(order: Order): Either<OrderError, OrderCancelledEvent>
}

class OrderPaymentCancellationDomainService : OrderPaymentCancellationService {
    companion object : CompanionLogger()

    override fun invoke(order: Order): Either<OrderError, OrderCancelledEvent> = either {
        // TODO("Refactor")
        OrderCancelledEvent(order.orderId.value, reason = "Payment has been cancelled")
            .log { info("Order payment is cancelling for order id: {}", order.orderId) }
    }
}