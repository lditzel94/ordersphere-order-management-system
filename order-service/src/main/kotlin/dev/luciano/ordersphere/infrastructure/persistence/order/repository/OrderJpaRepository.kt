package dev.luciano.ordersphere.infrastructure.persistence.order.repository

import dev.luciano.ordersphere.infrastructure.persistence.order.entity.OrderEntity
import java.util.Optional
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

interface OrderJpaRepository : JpaRepository<OrderEntity, UUID> {
    fun findByTrackingId(trackingId: UUID): Optional<OrderEntity>
}