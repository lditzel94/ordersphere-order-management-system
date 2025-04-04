package dev.luciano.ordersphere.application.dto.create

import dev.luciano.ordersphere.domain.valueobject.OrderState
import java.util.UUID

data class CreateOrderResponse(
    val orderTrackingId: UUID,
    val orderState: OrderState,
    val message: String
)
