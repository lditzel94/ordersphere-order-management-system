package dev.luciano.food.ordering.system.order.domain.service

import arrow.core.Either
import arrow.core.raise.either
import dev.luciano.food.ordering.system.common.configuration.logger.CompanionLogger
import dev.luciano.food.ordering.system.common.domain.UTC
import dev.luciano.food.ordering.system.order.domain.entity.Order
import dev.luciano.food.ordering.system.order.domain.entity.pay
import dev.luciano.food.ordering.system.order.domain.error.OrderError
import dev.luciano.food.ordering.system.order.domain.event.OrderPaidEvent
import java.time.ZoneId
import java.time.ZonedDateTime

fun interface PayOrderService {
    operator fun invoke(order: Order): Either<OrderError, OrderPaidEvent>
}

class PayOrderDomainService : PayOrderService {
    companion object : CompanionLogger()

    override fun invoke(order: Order): Either<OrderError, OrderPaidEvent> = either {
        OrderPaidEvent(order.pay().bind(), ZonedDateTime.now(ZoneId.of(UTC)))
            .log { info("Order with id: {} has been paid", order.orderId) }
    }
}