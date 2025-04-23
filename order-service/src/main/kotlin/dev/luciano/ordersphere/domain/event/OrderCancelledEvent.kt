package dev.luciano.ordersphere.domain.event

import java.time.ZonedDateTime

data class OrderCancelledEvent(
    override val createdAt: ZonedDateTime,
) : OrderEvent(createdAt)
