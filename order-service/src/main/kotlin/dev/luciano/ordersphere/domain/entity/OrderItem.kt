package dev.luciano.ordersphere.domain.entity

import dev.luciano.ordersphere.domain.valueobject.Money
import dev.luciano.ordersphere.domain.valueobject.OrderId
import dev.luciano.ordersphere.domain.valueobject.OrderItemId


data class OrderItem(
    val orderItemId: OrderItemId,
    val orderId: OrderId,
    val product: Product,
    val quantity: Int,
    val price: Money,
    val subTotal: Money,
) : BaseEntity<OrderItemId>(orderItemId) {
    init {
        require(quantity > 0) { "Quantity must be greater than zero" }
        require(price.isGreaterThanZero) { "Price must be greater than zero" }
        require(subTotal == price.times(quantity)) { "Subtotal must equal price multiplied by quantity" }
        require(price == product.price) { "Price must match product price" }
    }
}
