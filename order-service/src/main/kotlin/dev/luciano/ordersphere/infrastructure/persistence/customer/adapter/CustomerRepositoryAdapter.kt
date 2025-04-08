package dev.luciano.ordersphere.infrastructure.persistence.customer.adapter

import dev.luciano.ordersphere.application.port.output.repository.CustomerRepository
import dev.luciano.ordersphere.domain.entity.Customer
import dev.luciano.ordersphere.domain.valueobject.CustomerId
import dev.luciano.ordersphere.infrastructure.persistence.customer.mapper.customerEntityToCustomer
import dev.luciano.ordersphere.infrastructure.persistence.customer.mapper.customerToCustomerEntity
import dev.luciano.ordersphere.infrastructure.persistence.customer.repository.CustomerJpaRepository
import org.springframework.stereotype.Component

@Component
class CustomerRepositoryAdapter(
    private val customerJpaRepository: CustomerJpaRepository,
) : CustomerRepository {
    override fun findBy(customerId: CustomerId): Customer? =
        Customer(
            customerId = customerId,
            username = "Wilfredo Matthews",
            firstName = "Chandra Fowler",
            lastName = "Myrtle Flowers"
        )
//        customerJpaRepository.findById(customerId.value)
//            .map(customerEntityToCustomer::map)
//            .get()

    override fun save(customer: Customer): Customer =
        customerJpaRepository
            .save(customerToCustomerEntity.map(customer))
            .let { customerEntityToCustomer.map(it) }
}