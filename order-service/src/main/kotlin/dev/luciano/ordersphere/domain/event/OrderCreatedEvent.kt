package dev.luciano.ordersphere.domain.event

import dev.luciano.ordersphere.domain.entity.Order
import java.time.ZonedDateTime

data class OrderCreatedEvent(
    override val order: Order,
    override val createdAt: ZonedDateTime,
) : OrderEvent(order, createdAt)
