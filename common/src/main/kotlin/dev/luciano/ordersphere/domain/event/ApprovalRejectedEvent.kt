package dev.luciano.ordersphere.domain.event

import java.util.UUID

data class ApprovalRejectedEvent(val orderId: UUID) : ApprovalEvent