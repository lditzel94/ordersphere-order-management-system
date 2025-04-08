package dev.luciano.ordersphere.domain.entity

import dev.luciano.ordersphere.domain.valueobject.Money
import dev.luciano.ordersphere.domain.valueobject.ProductId

data class Product(
    val productId: ProductId,
    val name: String,
    val price: Money,
) : BaseEntity<ProductId>(productId)