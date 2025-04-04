package dev.luciano.ordersphere.domain.service

import arrow.core.Either
import arrow.core.raise.either
import dev.luciano.ordersphere.configuration.logger.CompanionLogger
import dev.luciano.ordersphere.domain.UTC
import dev.luciano.ordersphere.domain.entity.Order
import dev.luciano.ordersphere.domain.entity.initCancel
import dev.luciano.ordersphere.domain.error.OrderError
import dev.luciano.ordersphere.domain.event.OrderCancelledEvent
import java.time.ZoneId
import java.time.ZonedDateTime

fun interface OrderPaymentCancellationService {
    operator fun invoke(order: Order): Either<OrderError, OrderCancelledEvent>
}

class OrderPaymentCancellationDomainService : OrderPaymentCancellationService {
    companion object : CompanionLogger()

    override fun invoke(order: Order): Either<OrderError, OrderCancelledEvent> = either {
        OrderCancelledEvent(order.initCancel().bind(), ZonedDateTime.now(ZoneId.of(UTC)))
            .log { info("Order payment is cancelling for order id: {}", order.orderId) }
    }
}