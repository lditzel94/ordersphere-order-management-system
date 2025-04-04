package dev.luciano.ordersphere.application.port.output.repository

import dev.luciano.ordersphere.domain.entity.Customer
import dev.luciano.ordersphere.domain.valueobject.CustomerId

interface CustomerRepository {
    fun findBy(customerId: CustomerId): Customer?

    fun save(customer: Customer): Customer
}