package dev.luciano.food.ordering.system.order.domain.entity

import dev.luciano.food.ordering.system.common.domain.entity.AggregateRoot
import dev.luciano.food.ordering.system.common.domain.valueobject.RestaurantId

data class Restaurant(
    val restaurantId: RestaurantId,
    val products: List<Product> = emptyList(),
    val active: Boolean,
) : AggregateRoot<RestaurantId>(restaurantId)
