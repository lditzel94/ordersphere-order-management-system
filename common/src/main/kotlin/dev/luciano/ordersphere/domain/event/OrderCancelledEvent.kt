package dev.luciano.ordersphere.domain.event

import dev.luciano.ordersphere.domain.UTC
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.UUID

data class OrderCancelledEvent(override val orderId: UUID, val reason: String) : OrderEvent {
    override val createdAt = ZonedDateTime.now(ZoneId.of(UTC))
}
