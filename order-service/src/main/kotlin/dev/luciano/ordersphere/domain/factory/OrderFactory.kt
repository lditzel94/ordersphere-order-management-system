package dev.luciano.ordersphere.domain.factory

import dev.luciano.ordersphere.domain.entity.order.ClosedOrder
import dev.luciano.ordersphere.domain.entity.order.OpenOrder
import dev.luciano.ordersphere.domain.entity.order.OrderItem
import dev.luciano.ordersphere.domain.valueobject.CustomerId
import dev.luciano.ordersphere.domain.valueobject.Money
import dev.luciano.ordersphere.domain.valueobject.OrderId
import dev.luciano.ordersphere.domain.valueobject.OrderState
import dev.luciano.ordersphere.domain.valueobject.RestaurantId
import dev.luciano.ordersphere.domain.valueobject.StreetAddress
import dev.luciano.ordersphere.domain.valueobject.TrackingId

class OrderFactory {
    fun open(
        orderId: OrderId,
        customerId: CustomerId,
        restaurantId: RestaurantId,
        deliveryAddress: StreetAddress,
        price: Money,
        items: List<OrderItem>,
        trackingId: TrackingId,
        orderState: OrderState,
    ) = OpenOrder(
        orderId = orderId,
        customerId = customerId,
        restaurantId = restaurantId,
        deliveryAddress = deliveryAddress,
        trackingId = trackingId,
        state = orderState,
        price = price,
        items = items,
    )

    fun closed(
        orderId: OrderId,
        customerId: CustomerId,
        orderState: OrderState,
        price: Money,
        cancellationReason: String,
    ) = ClosedOrder(
        orderId = orderId,
        state = orderState,
        price = price,
        customerId = customerId,
        cancellationReason = cancellationReason,
    )
}

