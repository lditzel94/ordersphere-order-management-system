package dev.luciano.ordersphere.infrastructure.persistence.order.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.util.UUID

@Table(name = "order_address")
@Entity
data class OrderAddressEntity(
    @Id
    val id: UUID,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "ORDER_ID")
    val order: OrderEntity? = null,
    val street: String,
    val postalCode: String,
    val city: String,
)
