package dev.luciano.food.ordering.system.order.domain.event

import dev.luciano.food.ordering.system.order.domain.entity.Order
import java.time.ZonedDateTime

data class OrderCancelledEvent(
    override val order: Order,
    override val createdAt: ZonedDateTime,
) : OrderEvent(order, createdAt)
