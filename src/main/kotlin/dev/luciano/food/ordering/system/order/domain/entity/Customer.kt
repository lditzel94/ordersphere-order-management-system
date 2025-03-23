package dev.luciano.food.ordering.system.order.domain.entity

import dev.luciano.food.ordering.system.common.domain.entity.AggregateRoot
import dev.luciano.food.ordering.system.common.domain.valueobject.CustomerId

data class Customer(
    val customerId: CustomerId,
    val username: String,
    val firstName: String,
    val lastName: String,
) : AggregateRoot<CustomerId>(customerId)
