package dev.luciano.ordersphere.domain.entity.order

import dev.luciano.ordersphere.configuration.logger.CompanionLogger
import dev.luciano.ordersphere.domain.entity.BaseEntity
import dev.luciano.ordersphere.domain.valueobject.Money
import dev.luciano.ordersphere.domain.valueobject.OrderId
import dev.luciano.ordersphere.domain.valueobject.OrderItemId
import dev.luciano.ordersphere.domain.valueobject.ProductId


data class OrderItem(
    val orderItemId: OrderItemId,
    val orderId: OrderId,
    val productId: ProductId,
    val quantity: Int,
    val price: Money,
    val subTotal: Money,
) : BaseEntity<OrderItemId>(orderItemId) {

    companion object : CompanionLogger()

    init {
        require(quantity > 0) { "Item quantity must be greater than zero" }
        require(price.isGreaterThanZero) { "Item price must be greater than zero" }
        require(subTotal == price.times(quantity)) { "Item subtotal must equal price multiplied by quantity" }
    }
}
