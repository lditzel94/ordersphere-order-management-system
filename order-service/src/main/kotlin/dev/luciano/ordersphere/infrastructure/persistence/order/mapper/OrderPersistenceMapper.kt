package dev.luciano.ordersphere.infrastructure.persistence.order.mapper

import dev.luciano.ordersphere.configuration.mapper.Mapper
import dev.luciano.ordersphere.configuration.mapper.map
import dev.luciano.ordersphere.domain.entity.order.OpenOrder
import dev.luciano.ordersphere.domain.entity.order.Order
import dev.luciano.ordersphere.domain.entity.order.OrderItem
import dev.luciano.ordersphere.domain.valueobject.CustomerId
import dev.luciano.ordersphere.domain.valueobject.Money
import dev.luciano.ordersphere.domain.valueobject.OrderId
import dev.luciano.ordersphere.domain.valueobject.OrderItemId
import dev.luciano.ordersphere.domain.valueobject.OrderState.APPROVED
import dev.luciano.ordersphere.domain.valueobject.OrderState.CANCELLED
import dev.luciano.ordersphere.domain.valueobject.OrderState.CANCELLING
import dev.luciano.ordersphere.domain.valueobject.OrderState.PAID
import dev.luciano.ordersphere.domain.valueobject.OrderState.PENDING
import dev.luciano.ordersphere.domain.valueobject.ProductId
import dev.luciano.ordersphere.domain.valueobject.RestaurantId
import dev.luciano.ordersphere.domain.valueobject.StreetAddress
import dev.luciano.ordersphere.domain.valueobject.TrackingId
import dev.luciano.ordersphere.infrastructure.persistence.order.entity.OrderAddressEntity
import dev.luciano.ordersphere.infrastructure.persistence.order.entity.OrderEntity
import dev.luciano.ordersphere.infrastructure.persistence.order.entity.OrderItemEntity

val orderEntityToOrder = Mapper<OrderEntity, Order> { entity ->
    with(entity) {
        when (orderState) {
            PENDING, PAID, APPROVED -> Order.create {
                open(
                    orderId = OrderId(id),
                    customerId = CustomerId(customerId),
                    restaurantId = RestaurantId(restaurantId),
                    trackingId = TrackingId(trackingId),
                    price = Money(price),
                    deliveryAddress = addressEntityToStreetAddress.map(address),
                    items = orderItemEntityToOrderItem.map(items),
                    orderState = orderState
                )
            }

            CANCELLING, CANCELLED -> Order.create {
                closed(
                    orderId = OrderId(id),
                    customerId = CustomerId(customerId),
                    price = Money(price),
                    orderState = orderState,
                    cancellationReason = "Order cancelled"
                )
            }
        }
    }
}

val addressEntityToStreetAddress = Mapper<OrderAddressEntity, StreetAddress> { entity ->
    with(entity) {
        StreetAddress(
            id = id,
            street = street,
            postalCode = postalCode,
            city = city
        )
    }
}

val orderItemEntityToOrderItem = Mapper<OrderItemEntity, OrderItem> { entity ->
    with(entity) {
        OrderItem(
            orderItemId = OrderItemId(id),
            orderId = OrderId(order.id),
            productId = ProductId(productId),
            quantity = quantity,
            price = Money(price),
            subTotal = Money(subTotal)
        )
    }
}

val orderToOrderEntity = Mapper<Order, OrderEntity> { order ->
    with(order as OpenOrder) {
        OrderEntity(
            id = orderId.value,
            orderState = state,
            customerId = customerId.value,
            restaurantId = restaurantId.value,
            trackingId = trackingId.value,
            price = price.amount
        ).also {
            it.items = orderItemToOrderItemEntity(items, it)
            it.address = streetAddressToOrderAddressEntity(deliveryAddress, it)
        }
    }
}

val streetAddressToOrderAddressEntity = { address: StreetAddress, orderEntity: OrderEntity ->
    with(address) {
        OrderAddressEntity(
            id = id,
            street = street,
            postalCode = postalCode,
            city = city,
            order = orderEntity
        )
    }
}

val orderItemToOrderItemEntity = { items: List<OrderItem>, orderEntity: OrderEntity ->
    items.mapTo(mutableListOf()) {
        OrderItemEntity(
            id = it.orderItemId.value,
            productId = it.productId.value,
            quantity = it.quantity,
            price = it.price.amount,
            subTotal = it.subTotal.amount,
            order = orderEntity
        )
    }
}