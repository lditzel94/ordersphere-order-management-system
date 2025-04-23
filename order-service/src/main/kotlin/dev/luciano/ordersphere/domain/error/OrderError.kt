package dev.luciano.ordersphere.domain.error

import dev.luciano.ordersphere.domain.valueobject.OrderId

sealed class OrderError(val message: String) : Error {
    override fun message(): String = message
}

data class OrderDomainError(val error: String) : OrderError(message = error)

data class OrderNotFoundError(val id: OrderId) : OrderError(message = "Order with id=${id.value} not found")