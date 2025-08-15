package dev.luciano.ordersphere.domain.event

import java.time.ZonedDateTime
import java.util.UUID

sealed interface OrderEvent{
    val orderId: UUID
    val createdAt: ZonedDateTime
}