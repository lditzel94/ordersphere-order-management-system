package dev.luciano.ordersphere.domain.service

import arrow.core.Either
import arrow.core.raise.Raise
import arrow.core.raise.either
import arrow.core.raise.ensure
import dev.luciano.ordersphere.configuration.logger.CompanionLogger
import dev.luciano.ordersphere.domain.entity.Restaurant
import dev.luciano.ordersphere.domain.entity.order.OpenOrder
import dev.luciano.ordersphere.domain.error.OrderDomainError
import dev.luciano.ordersphere.domain.error.OrderError
import dev.luciano.ordersphere.domain.event.OrderCreatedEvent

fun interface OrderCreationService {
    operator fun invoke(order: OpenOrder, restaurant: Restaurant): Either<OrderError, OrderCreatedEvent>
}

class OrderCreationDomainService : OrderCreationService {
    companion object : CompanionLogger()

    override fun invoke(order: OpenOrder, restaurant: Restaurant): Either<OrderError, OrderCreatedEvent> = either {
        with(order) {
            OrderCreatedEvent(orderId.value, trackingId.value, state)
        }.also { ensureRestaurantIsActive(restaurant) }
            .log { info("Order with id: {} initiated", order.orderId.value) }
    }

    private fun Raise<OrderError>.ensureRestaurantIsActive(restaurant: Restaurant) =
        ensure(restaurant.active) {
            OrderDomainError("Restaurant with id=${restaurant.restaurantId} is not active yet")
        }
}