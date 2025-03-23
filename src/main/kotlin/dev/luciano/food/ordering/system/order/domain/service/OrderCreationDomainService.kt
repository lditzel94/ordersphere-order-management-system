package dev.luciano.food.ordering.system.order.domain.service

import arrow.core.Either
import arrow.core.raise.Raise
import arrow.core.raise.either
import arrow.core.raise.ensure
import dev.luciano.food.ordering.system.common.configuration.logger.CompanionLogger
import dev.luciano.food.ordering.system.common.domain.UTC
import dev.luciano.food.ordering.system.order.domain.entity.Order
import dev.luciano.food.ordering.system.order.domain.entity.Restaurant
import dev.luciano.food.ordering.system.order.domain.entity.initiate
import dev.luciano.food.ordering.system.order.domain.error.OrderDomainError
import dev.luciano.food.ordering.system.order.domain.error.OrderError
import dev.luciano.food.ordering.system.order.domain.event.OrderCreatedEvent

import java.time.ZoneId
import java.time.ZonedDateTime

fun interface OrderCreationService {
    operator fun invoke(order: Order, restaurant: Restaurant): Either<OrderError, OrderCreatedEvent>
}

class OrderCreationDomainService : OrderCreationService {
    companion object : CompanionLogger()

    override fun invoke(order: Order, restaurant: Restaurant): Either<OrderError, OrderCreatedEvent> = either {
        OrderCreatedEvent(order.initiate(), ZonedDateTime.now(ZoneId.of(UTC)))
            .also { ensureRestaurantIsActive(restaurant) }
            .log { info("Order with id: {} is initiated", order.orderId) }
    }

    private fun Raise<OrderError>.ensureRestaurantIsActive(restaurant: Restaurant) =
        ensure(restaurant.active) {
            OrderDomainError("Restaurant with id=${restaurant.restaurantId} is not active yet")
        }
}