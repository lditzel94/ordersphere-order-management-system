package dev.luciano.ordersphere.application.service.create

import arrow.core.Either
import arrow.core.raise.either
import dev.luciano.ordersphere.application.dto.create.CreateOrderCommand
import dev.luciano.ordersphere.application.dto.create.CreateOrderResponse
import dev.luciano.ordersphere.application.mapper.orderToCreateOrderResponse
import dev.luciano.ordersphere.application.port.output.message.publisher.OrderCreatedEventPublisher
import dev.luciano.ordersphere.application.usecase.CreateOrder
import dev.luciano.ordersphere.configuration.logger.CompanionLogger
import dev.luciano.ordersphere.domain.error.OrderError
import dev.luciano.ordersphere.domain.event.OrderCreatedEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
open class OrderCreationCommandHandler(
    private val createOrder: CreateOrder,
    private val saga: OrderCreationSagaOrchestrator,
) {
    companion object : CompanionLogger()

    @Transactional
    open suspend fun handle(command: CreateOrderCommand): Either<OrderError, CreateOrderResponse> = either {
        createOrder(command)
            .onRight { saga.start(it) }
            .map { orderToCreateOrderResponse.map(it) }
            .bind()


        //TODO: Implement outbox message
    }
}