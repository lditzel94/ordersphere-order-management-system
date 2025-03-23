package dev.luciano.food.ordering.system.order.domain.error

import dev.luciano.food.ordering.system.common.domain.valueobject.OrderId

sealed class OrderError(val message: String)

data class OrderDomainError(val error: String) : OrderError(message = error)

data class OrderNotFoundError(val id: OrderId) : OrderError(message = "Order with id=${id.value} not found")