package dev.luciano.ordersphere.application.port.output.repository

import dev.luciano.ordersphere.domain.entity.Restaurant
import dev.luciano.ordersphere.domain.valueobject.ProductId
import dev.luciano.ordersphere.domain.valueobject.RestaurantId

interface RestaurantRepository {
    fun findRestaurantInformation(restaurantId: RestaurantId, productIds: List<ProductId>): Restaurant
}