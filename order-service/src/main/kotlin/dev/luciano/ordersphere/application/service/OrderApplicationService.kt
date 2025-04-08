package dev.luciano.ordersphere.application.service

import arrow.core.Either
import dev.luciano.ordersphere.application.dto.create.CreateOrderCommand
import dev.luciano.ordersphere.application.dto.create.CreateOrderResponse
import dev.luciano.ordersphere.application.port.input.service.OrderService
import dev.luciano.ordersphere.application.service.create.OrderCreationCommandHandler
import dev.luciano.ordersphere.domain.error.OrderError
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

@Validated
@Service
class OrderApplicationService(
    private val orderCreationCommandHandler: OrderCreationCommandHandler,
) : OrderService {
    override suspend fun createOrder(createOrderCommand: CreateOrderCommand): Either<OrderError, CreateOrderResponse> =
        orderCreationCommandHandler.handle(createOrderCommand)

    override fun trackOrder() {
        TODO("Not yet implemented")
    }
}