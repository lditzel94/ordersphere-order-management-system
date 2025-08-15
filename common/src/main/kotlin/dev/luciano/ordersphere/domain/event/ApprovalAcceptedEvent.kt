package dev.luciano.ordersphere.domain.event

import java.util.UUID

data class ApprovalAcceptedEvent(val orderId: UUID) : ApprovalEvent
