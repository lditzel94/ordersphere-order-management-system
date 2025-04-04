package dev.luciano.ordersphere.application.dto.create

import java.math.BigDecimal
import java.util.UUID

data class CreateOrderCommand(
    val customerId: UUID,
    val restaurantId: UUID,
    val price: BigDecimal,
    val items: List<OrderItem>,
    val address: OrderAddress,
){
    data class OrderItem(
        val productId: UUID,
        val quantity: Int,
        val price: BigDecimal,
        val subTotal: BigDecimal,
    )
}
