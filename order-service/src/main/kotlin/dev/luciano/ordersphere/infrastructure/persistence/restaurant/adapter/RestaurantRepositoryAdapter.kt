package dev.luciano.ordersphere.infrastructure.persistence.restaurant.adapter

import dev.luciano.ordersphere.application.port.output.repository.RestaurantRepository
import dev.luciano.ordersphere.domain.entity.Restaurant
import dev.luciano.ordersphere.domain.valueobject.ProductId
import dev.luciano.ordersphere.domain.valueobject.RestaurantId
import org.springframework.stereotype.Component

@Component
class RestaurantRepositoryAdapter : RestaurantRepository {
    override fun findRestaurantInformation(restaurantId: RestaurantId, productIds: List<ProductId>): Restaurant {
        TODO("Not yet implemented")
    }
}