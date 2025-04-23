package dev.luciano.ordersphere.domain.event

import dev.luciano.ordersphere.domain.UTC
import dev.luciano.ordersphere.domain.entity.Order
import dev.luciano.ordersphere.domain.valueobject.OrderState
import dev.luciano.ordersphere.domain.valueobject.StreetAddress
import java.math.BigDecimal
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.UUID

data class OrderCreatedEvent(
    override val createdAt: ZonedDateTime,
    val orderId: UUID,
    val price: BigDecimal,
    val customerId: UUID,
    val restaurantId: UUID,
    val deliveryAddress: StreetAddress,
    val trackingId: UUID,
    val orderState: OrderState,
) : OrderEvent(createdAt) {
    companion object {
        fun from(order: Order.Pending) = with(order) {
            OrderCreatedEvent(
                createdAt = ZonedDateTime.now(ZoneId.of(UTC)),
                orderId = orderId.value,
                price = price.amount,
                customerId = customerId.value,
                restaurantId = restaurantId.value,
                deliveryAddress = deliveryAddress,
                trackingId = trackingId.value,
                orderState = orderState,
            )
        }
    }
}