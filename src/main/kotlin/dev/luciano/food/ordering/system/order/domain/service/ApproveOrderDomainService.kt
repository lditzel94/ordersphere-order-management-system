package dev.luciano.food.ordering.system.order.domain.service

import arrow.core.Either
import arrow.core.raise.either
import dev.luciano.food.ordering.system.common.configuration.logger.CompanionLogger
import dev.luciano.food.ordering.system.order.domain.entity.Order
import dev.luciano.food.ordering.system.order.domain.entity.approve
import dev.luciano.food.ordering.system.order.domain.error.OrderError


fun interface ApproveOrderService {
    operator fun invoke(order: Order): Either<OrderError, Unit>
}

class ApproveOrderDomainService : ApproveOrderService {
    companion object : CompanionLogger()

    override fun invoke(order: Order): Either<OrderError, Unit> = either {
        order.approve()
            .log { info("Order with id: {} has been approved", order.orderId) }
    }
}