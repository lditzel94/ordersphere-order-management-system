package dev.luciano.food.ordering.system.order.domain.event

import dev.luciano.food.ordering.system.common.domain.event.DomainEvent
import dev.luciano.food.ordering.system.order.domain.entity.Order
import java.time.ZonedDateTime

sealed class OrderEvent(
    open val order: Order,
    open val createdAt: ZonedDateTime,
) : DomainEvent<Order>