package dev.luciano.ordersphere.infrastructure.persistence.customer.adapter

import dev.luciano.ordersphere.application.port.output.repository.CustomerRepository
import dev.luciano.ordersphere.domain.entity.Customer
import dev.luciano.ordersphere.domain.valueobject.CustomerId
import org.springframework.stereotype.Component

@Component
class CustomerRepositoryAdapter : CustomerRepository {
    override fun findBy(customerId: CustomerId): Customer {
        TODO("Not yet implemented")
    }

    override fun save(customer: Customer): Customer {
        TODO("Not yet implemented")
    }
}