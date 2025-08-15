package dev.luciano.ordersphere.domain.event

import dev.luciano.ordersphere.domain.UTC
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.UUID

data class OrderPaidEvent(override val orderId: UUID) : OrderEvent {
    override val createdAt = ZonedDateTime.now(ZoneId.of(UTC))
}
