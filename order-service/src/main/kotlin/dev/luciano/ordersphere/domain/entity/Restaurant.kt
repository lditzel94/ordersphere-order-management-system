package dev.luciano.ordersphere.domain.entity

import dev.luciano.ordersphere.domain.valueobject.RestaurantId

data class Restaurant(
    val restaurantId: RestaurantId,
    val products: List<Product> = emptyList(),
    val active: Boolean = false,
) : AggregateRoot<RestaurantId>(restaurantId)
