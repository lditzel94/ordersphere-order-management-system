package dev.luciano.ordersphere.domain.service

import arrow.core.Either
import arrow.core.raise.either
import dev.luciano.ordersphere.configuration.logger.CompanionLogger
import dev.luciano.ordersphere.domain.entity.order.Order
import dev.luciano.ordersphere.domain.entity.order.cancel
import dev.luciano.ordersphere.domain.error.OrderError

fun interface OrderCancellationService {
    operator fun invoke(order: Order): Either<OrderError, Unit>
}

class OrderCancellationDomainService : OrderCancellationService {
    companion object : CompanionLogger()

    override fun invoke(order: Order): Either<OrderError, Unit> = either {
        order.cancel().log { info("Order with id: {} has been cancelled", order.orderId) }
    }
}