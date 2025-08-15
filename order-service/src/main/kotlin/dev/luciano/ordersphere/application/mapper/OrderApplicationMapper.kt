package dev.luciano.ordersphere.application.mapper

import dev.luciano.ordersphere.application.dto.create.CreateOrderCommand
import dev.luciano.ordersphere.application.dto.create.CreateOrderResponse
import dev.luciano.ordersphere.application.dto.create.OrderAddress
import dev.luciano.ordersphere.configuration.mapper.Mapper
import dev.luciano.ordersphere.domain.entity.order.Order
import dev.luciano.ordersphere.domain.entity.order.OrderItem
import dev.luciano.ordersphere.domain.event.OrderCreatedEvent
import dev.luciano.ordersphere.domain.valueobject.CustomerId
import dev.luciano.ordersphere.domain.valueobject.Money
import dev.luciano.ordersphere.domain.valueobject.OrderId
import dev.luciano.ordersphere.domain.valueobject.OrderItemId
import dev.luciano.ordersphere.domain.valueobject.OrderState.PENDING
import dev.luciano.ordersphere.domain.valueobject.ProductId
import dev.luciano.ordersphere.domain.valueobject.RestaurantId
import dev.luciano.ordersphere.domain.valueobject.StreetAddress
import dev.luciano.ordersphere.domain.valueobject.TrackingId
import java.util.UUID


val createOrderCommandToOrder = Mapper<CreateOrderCommand, Order> {
    with(it) {
        val orderId = OrderId(value = UUID.randomUUID())

        Order.create {
            open(
                orderId = orderId,
                customerId = CustomerId(value = customerId),
                restaurantId = RestaurantId(value = restaurantId),
                deliveryAddress = orderAddressToStreeAddress.map(address),
                price = Money(amount = price),
                items = orderItemsToOrderItemEntities(items, orderId),
                trackingId = TrackingId(UUID.randomUUID()),
                orderState = PENDING,
            )
        }
    }
}

val orderAddressToStreeAddress = Mapper<OrderAddress, StreetAddress> {
    with(it) {
        StreetAddress(
            id = UUID.randomUUID(),
            street = street,
            postalCode = postalCode,
            city = city
        )
    }
}

val orderItemsToOrderItemEntities = { items: List<CreateOrderCommand.OrderItem>, orderId: OrderId ->
    items.mapIndexed { index, item ->
        OrderItem(
            orderItemId = OrderItemId(value = (index + 1).toLong()),
            orderId = orderId,
            productId = ProductId(item.productId),
            quantity = item.quantity,
            price = Money(amount = item.price),
            subTotal = Money(amount = item.subTotal)
        )
    }
}

val orderToCreateOrderResponse = Mapper<OrderCreatedEvent, CreateOrderResponse> {
    with(it) {
        CreateOrderResponse(
            orderTrackingId = trackingId,
            orderState = state,
            message = "Order created successfully",
        )
    }
}