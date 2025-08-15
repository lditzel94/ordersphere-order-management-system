package dev.luciano.ordersphere.domain.entity.order

import dev.luciano.ordersphere.domain.valueobject.CustomerId
import dev.luciano.ordersphere.domain.valueobject.Money
import dev.luciano.ordersphere.domain.valueobject.OrderId
import dev.luciano.ordersphere.domain.valueobject.OrderState
import dev.luciano.ordersphere.domain.valueobject.RestaurantId
import dev.luciano.ordersphere.domain.valueobject.StreetAddress
import dev.luciano.ordersphere.domain.valueobject.TrackingId

data class OpenOrder(
    override val orderId: OrderId,
    override val state: OrderState,
    override val price: Money,
    override val customerId: CustomerId,
    val items: List<OrderItem>,
    val restaurantId: RestaurantId,
    val deliveryAddress: StreetAddress,
    val trackingId: TrackingId,
) : Order {
    init {
        require(price.isGreaterThanZero) { "Order price must be greater than zero" }
        require(items.sumOf { it.subTotal.amount } == price.amount) {
            "The sum of item subtotals does not match the order price=${price.amount}"
        }
    }
}