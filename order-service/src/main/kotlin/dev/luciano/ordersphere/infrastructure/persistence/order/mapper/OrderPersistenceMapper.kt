package dev.luciano.ordersphere.infrastructure.persistence.order.mapper

import dev.luciano.ordersphere.configuration.mapper.Mapper
import dev.luciano.ordersphere.configuration.mapper.map
import dev.luciano.ordersphere.domain.entity.Order
import dev.luciano.ordersphere.domain.entity.OrderItem
import dev.luciano.ordersphere.domain.entity.Product
import dev.luciano.ordersphere.domain.valueobject.CustomerId
import dev.luciano.ordersphere.domain.valueobject.Money
import dev.luciano.ordersphere.domain.valueobject.OrderId
import dev.luciano.ordersphere.domain.valueobject.OrderItemId
import dev.luciano.ordersphere.domain.valueobject.ProductId
import dev.luciano.ordersphere.domain.valueobject.RestaurantId
import dev.luciano.ordersphere.domain.valueobject.StreetAddress
import dev.luciano.ordersphere.domain.valueobject.TrackingId
import dev.luciano.ordersphere.infrastructure.persistence.order.entity.OrderAddressEntity
import dev.luciano.ordersphere.infrastructure.persistence.order.entity.OrderEntity
import dev.luciano.ordersphere.infrastructure.persistence.order.entity.OrderItemEntity

val orderEntityToOrder = Mapper<OrderEntity, Order> { entity ->
    with(entity) {
        Order.from(
            orderState = orderState,
            orderId = OrderId(id),
            customerId = CustomerId(customerId),
            restaurantId = RestaurantId(restaurantId),
            trackingId = TrackingId(trackingId),
            orderPrice = Money(price),
            deliveryAddress = addressEntityToStreetAddress.map(address),
            orderItems = orderItemEntityToOrderItem.map(items)
        )
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
            orderId = OrderId(order!!.id),
            product = Product(ProductId(productId)),
            quantity = quantity,
            price = Money(price),
            subTotal = Money(subTotal)
        )
    }
}

val orderToOrderEntity = Mapper<Order, OrderEntity> { order ->
    with(order) {
        OrderEntity(
            id = orderId.value,
            orderState = orderState,
            customerId = customerId.value,
            restaurantId = restaurantId.value,
            trackingId = trackingId.value,
            price = price.amount,
            address = streetAddressToOrderAddressEntity.map(deliveryAddress),
            items = orderItemToOrderItemEntity.map(items)
        )
    }
}

val streetAddressToOrderAddressEntity = Mapper<StreetAddress, OrderAddressEntity> { address ->
    with(address) {
        OrderAddressEntity(
            id = id,
            street = street,
            postalCode = postalCode,
            city = city,
        )
    }
}

val orderItemToOrderItemEntity = Mapper<OrderItem, OrderItemEntity> { item ->
    with(item) {
        OrderItemEntity(
            id = orderItemId.value,
            productId = product.productId.value,
            quantity = quantity,
            price = price.amount,
            subTotal = subTotal.amount
        )
    }
}