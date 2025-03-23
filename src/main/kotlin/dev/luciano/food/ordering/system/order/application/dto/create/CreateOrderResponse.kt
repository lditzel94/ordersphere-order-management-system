package dev.luciano.food.ordering.system.order.application.dto.create

import dev.luciano.food.ordering.system.common.domain.valueobject.OrderState
import java.util.UUID

data class CreateOrderResponse(
    val orderTrackingId: UUID,
    val orderState: OrderState,
    val message: String
)
