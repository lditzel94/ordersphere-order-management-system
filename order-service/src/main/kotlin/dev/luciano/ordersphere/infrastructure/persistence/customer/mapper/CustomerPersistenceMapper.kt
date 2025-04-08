package dev.luciano.ordersphere.infrastructure.persistence.customer.mapper

import dev.luciano.ordersphere.configuration.mapper.Mapper
import dev.luciano.ordersphere.domain.entity.Customer
import dev.luciano.ordersphere.domain.valueobject.CustomerId
import dev.luciano.ordersphere.infrastructure.persistence.customer.entity.CustomerEntity

val customerEntityToCustomer = Mapper<CustomerEntity, Customer> { entity ->
    with(entity) {
        Customer(
            customerId = CustomerId(id),
            username = username,
            firstName = firstName,
            lastName = lastName
        )
    }
}

val customerToCustomerEntity = Mapper<Customer, CustomerEntity> { customer ->
    with(customer) {
        CustomerEntity(
            id = customerId.value,
            username = username,
            firstName = firstName,
            lastName = lastName
        )
    }
}