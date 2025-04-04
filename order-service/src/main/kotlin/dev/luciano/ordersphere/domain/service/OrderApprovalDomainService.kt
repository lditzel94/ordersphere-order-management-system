package dev.luciano.ordersphere.domain.service

import arrow.core.Either
import arrow.core.raise.either
import dev.luciano.ordersphere.configuration.logger.CompanionLogger
import dev.luciano.ordersphere.domain.entity.Order
import dev.luciano.ordersphere.domain.entity.approve
import dev.luciano.ordersphere.domain.error.OrderError

fun interface OrderApprovalService {
    operator fun invoke(order: Order): Either<OrderError, Unit>
}

class OrderApprovalDomainService : OrderApprovalService {
    companion object : CompanionLogger()

    override fun invoke(order: Order): Either<OrderError, Unit> = either {
        order.approve()
            .log { info("Order with id: {} has been approved", order.orderId) }
    }
}