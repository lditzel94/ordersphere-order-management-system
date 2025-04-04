package dev.luciano.ordersphere.application.port.output.repository

import dev.luciano.ordersphere.domain.entity.Order
import dev.luciano.ordersphere.domain.valueobject.OrderId
import dev.luciano.ordersphere.domain.valueobject.TrackingId

interface OrderRepository {
    fun save(order: Order): Order

    fun findBy(orderId: OrderId): Order?

    fun findBy(trackingId: TrackingId): Order?
}