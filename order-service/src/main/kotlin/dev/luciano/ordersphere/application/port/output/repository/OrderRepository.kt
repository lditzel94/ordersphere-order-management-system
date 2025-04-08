package dev.luciano.ordersphere.application.port.output.repository

import dev.luciano.ordersphere.domain.entity.Order
import dev.luciano.ordersphere.domain.valueobject.OrderId
import dev.luciano.ordersphere.domain.valueobject.TrackingId

interface OrderRepository {
    suspend fun save(order: Order): Order

    suspend fun findBy(orderId: OrderId): Order?

    suspend fun findBy(trackingId: TrackingId): Order?
}