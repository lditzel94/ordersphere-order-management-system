package dev.luciano.food.ordering.system.order.domain.entity

import dev.luciano.food.ordering.system.common.domain.entity.BaseEntity
import dev.luciano.food.ordering.system.common.domain.valueobject.Money
import dev.luciano.food.ordering.system.common.domain.valueobject.ProductId

data class Product(
    val productId: ProductId,
    val name: String,
    val price: Money,
): BaseEntity<ProductId>(productId)