package dev.luciano.ordersphere.application.port.input.service

import arrow.core.Either
import dev.luciano.ordersphere.application.dto.create.CreateOrderCommand
import dev.luciano.ordersphere.application.dto.create.CreateOrderResponse
import dev.luciano.ordersphere.domain.error.OrderError
import jakarta.validation.Valid

interface OrderService {
    suspend fun createOrder(@Valid createOrderCommand: CreateOrderCommand): Either<OrderError, CreateOrderResponse>

    fun trackOrder()
}