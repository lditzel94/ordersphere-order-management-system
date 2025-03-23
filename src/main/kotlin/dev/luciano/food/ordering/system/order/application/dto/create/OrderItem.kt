package dev.luciano.food.ordering.system.order.application.dto.create

import java.math.BigDecimal
import java.util.UUID

data class OrderItem(
    val productId: UUID,
    val quantity: Int,
    val price: BigDecimal,
    val subTotal: BigDecimal,
)
