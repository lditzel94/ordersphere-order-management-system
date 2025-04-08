package dev.luciano.ordersphere.infrastructure.persistence.customer.repository

import dev.luciano.ordersphere.infrastructure.persistence.customer.entity.CustomerEntity
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerJpaRepository : JpaRepository<CustomerEntity, UUID> {
}