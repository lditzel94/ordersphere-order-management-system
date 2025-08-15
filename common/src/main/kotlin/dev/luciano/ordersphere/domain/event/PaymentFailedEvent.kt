package dev.luciano.ordersphere.domain.event

import java.util.UUID

data class PaymentFailedEvent(val orderId: UUID) : PaymentEvent
