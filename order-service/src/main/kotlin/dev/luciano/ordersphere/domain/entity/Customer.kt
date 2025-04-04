package dev.luciano.ordersphere.domain.entity

import dev.luciano.ordersphere.domain.valueobject.CustomerId

data class Customer(
    val customerId: CustomerId,
    val username: String,
    val firstName: String,
    val lastName: String,
) : AggregateRoot<CustomerId>(customerId)
