package dev.luciano.ordersphere.infrastructure.persistence.order.entity

import dev.luciano.ordersphere.domain.valueobject.OrderState
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.math.BigDecimal
import java.util.UUID

@Table(name = "orders")
@Entity
data class OrderEntity(
    @Id
    val id: UUID,
    val customerId: UUID,
    val restaurantId: UUID,
    val trackingId: UUID,
    val price: BigDecimal,
    @Enumerated(EnumType.STRING)
    val orderState: OrderState,
    @OneToOne(mappedBy = "order", cascade = [CascadeType.ALL])
    val address: OrderAddressEntity,
    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    val items: List<OrderItemEntity>,
)