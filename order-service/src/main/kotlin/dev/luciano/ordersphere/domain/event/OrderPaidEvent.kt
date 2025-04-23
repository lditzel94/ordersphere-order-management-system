package dev.luciano.ordersphere.domain.event

import java.time.ZonedDateTime

data class OrderPaidEvent(
    override val createdAt: ZonedDateTime,
) : OrderEvent(createdAt)
