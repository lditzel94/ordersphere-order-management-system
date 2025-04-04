package dev.luciano.ordersphere.domain.service

import arrow.core.Either
import arrow.core.raise.either
import dev.luciano.ordersphere.configuration.logger.CompanionLogger
import dev.luciano.ordersphere.domain.UTC
import dev.luciano.ordersphere.domain.entity.Order
import dev.luciano.ordersphere.domain.entity.pay
import dev.luciano.ordersphere.domain.error.OrderError
import dev.luciano.ordersphere.domain.event.OrderPaidEvent
import java.time.ZoneId
import java.time.ZonedDateTime

fun interface OrderPaymentService {
    operator fun invoke(order: Order): Either<OrderError, OrderPaidEvent>
}

class OrderPaymentDomainService : OrderPaymentService {
    companion object : CompanionLogger()

    override fun invoke(order: Order): Either<OrderError, OrderPaidEvent> = either {
        OrderPaidEvent(order.pay().bind(), ZonedDateTime.now(ZoneId.of(UTC)))
            .log { info("Order with id: {} has been paid", order.orderId) }
    }
}