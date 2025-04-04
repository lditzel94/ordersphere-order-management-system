package dev.luciano.ordersphere.domain.event

import dev.luciano.ordersphere.domain.entity.Order
import java.time.ZonedDateTime

sealed class OrderEvent(
    open val order: Order,
    open val createdAt: ZonedDateTime,
) : DomainEvent<Order>