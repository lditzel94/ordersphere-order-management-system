package dev.luciano.ordersphere.infrastructure.persistence.restaurant.adapter

import dev.luciano.ordersphere.application.port.output.repository.RestaurantRepository
import dev.luciano.ordersphere.domain.entity.Product
import dev.luciano.ordersphere.domain.entity.Restaurant
import dev.luciano.ordersphere.domain.valueobject.Money
import dev.luciano.ordersphere.domain.valueobject.ProductId
import dev.luciano.ordersphere.domain.valueobject.RestaurantId
import java.math.BigDecimal
import java.math.BigDecimal.TEN
import java.util.UUID
import org.springframework.stereotype.Component

@Component
class RestaurantRepositoryAdapter : RestaurantRepository {
    override fun findRestaurantInformation(restaurantId: RestaurantId, productIds: List<ProductId>): Restaurant? =
        Restaurant(
            restaurantId = restaurantId,
            products = productIds.mapIndexed { index, productId -> Product(productId = productId, name = "Producto $index", price = Money(TEN)) },
            active = true
        )
}