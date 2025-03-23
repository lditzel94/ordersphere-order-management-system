package dev.luciano.food.ordering.system.order.domain.service

import arrow.core.Either
import arrow.core.raise.either
import dev.luciano.food.ordering.system.common.configuration.logger.CompanionLogger
import dev.luciano.food.ordering.system.common.domain.UTC
import dev.luciano.food.ordering.system.order.domain.entity.Order
import dev.luciano.food.ordering.system.order.domain.entity.initCancel
import dev.luciano.food.ordering.system.order.domain.error.OrderError
import dev.luciano.food.ordering.system.order.domain.event.OrderCancelledEvent
import java.time.ZoneId
import java.time.ZonedDateTime

fun interface CancelOrderPaymentService {
    operator fun invoke(order: Order): Either<OrderError, OrderCancelledEvent>
}

class CancelOrderPaymentDomainService : CancelOrderPaymentService {
    companion object : CompanionLogger()

    override fun invoke(order: Order): Either<OrderError, OrderCancelledEvent> = either {
        OrderCancelledEvent(order.initCancel().bind(), ZonedDateTime.now(ZoneId.of(UTC)))
            .log { info("Order payment is cancelling for order id: {}", order.orderId) }
    }
}