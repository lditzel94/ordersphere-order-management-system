package dev.luciano.ordersphere.domain.event

import dev.luciano.ordersphere.domain.UTC
import dev.luciano.ordersphere.domain.valueobject.OrderState
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.UUID

data class OrderCreatedEvent(
    override val orderId: UUID,
    val trackingId: UUID,
    val state: OrderState,
) : OrderEvent {
    override val createdAt = ZonedDateTime.now(ZoneId.of(UTC))
}