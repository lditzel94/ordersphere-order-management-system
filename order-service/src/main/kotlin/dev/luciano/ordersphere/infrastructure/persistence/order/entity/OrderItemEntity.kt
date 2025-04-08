package dev.luciano.ordersphere.infrastructure.persistence.order.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal
import java.util.UUID

//@IdClass(OrderItemEntityId::class)
@Table(name = "order_items")
@Entity
class OrderItemEntity(
//    @EmbeddedId
//    var id: OrderItemEntityId,
    @Id
    var id: Long,
    @Id
    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "ORDER_ID")
    var order: OrderEntity,
    var productId: UUID,
    var price: BigDecimal,
    var quantity: Int,
    var subTotal: BigDecimal,
)
