package dev.luciano.food.ordering.system.order.domain.service

import arrow.core.Either
import arrow.core.raise.either
import dev.luciano.food.ordering.system.common.configuration.logger.CompanionLogger
import dev.luciano.food.ordering.system.order.domain.entity.Order
import dev.luciano.food.ordering.system.order.domain.entity.cancel
import dev.luciano.food.ordering.system.order.domain.error.OrderError

fun interface CancelOrderService {
    operator fun invoke(order: Order): Either<OrderError, Unit>
}

class CancelOrderDomainService : CancelOrderService {
    companion object : CompanionLogger()

    override fun invoke(order: Order): Either<OrderError, Unit> = either {
        order.cancel().log { info("Order with id: {} has been cancelled", order.orderId) }
    }
}