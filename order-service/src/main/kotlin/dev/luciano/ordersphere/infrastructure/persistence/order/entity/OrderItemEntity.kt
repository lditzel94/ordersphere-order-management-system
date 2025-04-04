package dev.luciano.ordersphere.infrastructure.persistence.order.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal
import java.util.UUID

@IdClass(OrderItemEntityId::class)
@Table(name = "order_items")
@Entity
data class OrderItemEntity(
    @Id
    val id: Long,
    @Id
    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "ORDER_ID")
    val order: OrderEntity? = null,
    val productId: UUID,
    val price: BigDecimal,
    val quantity: Int,
    val subTotal: BigDecimal,
)
