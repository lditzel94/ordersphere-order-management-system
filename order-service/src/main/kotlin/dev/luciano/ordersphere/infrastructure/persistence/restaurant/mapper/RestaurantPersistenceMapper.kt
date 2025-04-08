package dev.luciano.ordersphere.infrastructure.persistence.restaurant.mapper

import dev.luciano.ordersphere.configuration.mapper.Mapper
import dev.luciano.ordersphere.domain.entity.Restaurant
import java.util.UUID

val restaurantToProductIds = Mapper<Restaurant, List<UUID>> { restaurant ->
    restaurant.products.map { it.productId.value }
}
