package dev.luciano.ordersphere.domain.event

import java.util.UUID

data class PaymentCompletedEvent(val orderId: UUID): PaymentEvent