package dev.luciano.food.ordering.system.order.application.port.input.service

import dev.luciano.food.ordering.system.order.application.dto.create.CreateOrderCommand
import dev.luciano.food.ordering.system.order.application.dto.create.CreateOrderResponse
import jakarta.validation.Valid

interface OrderService {
    fun createOrder(@Valid createOrderCommand: CreateOrderCommand): CreateOrderResponse

    fun trackOrder()
}