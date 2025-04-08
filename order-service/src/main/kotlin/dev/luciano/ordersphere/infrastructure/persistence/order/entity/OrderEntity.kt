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
class OrderEntity(
    @Id
    var id: UUID,
    var customerId: UUID,
    var restaurantId: UUID,
    var trackingId: UUID,
    var price: BigDecimal,
    @Enumerated(EnumType.STRING)
    var orderState: OrderState,
    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    var items: MutableList<OrderItemEntity> = mutableListOf(),
) {
    @OneToOne(mappedBy = "order", cascade = [CascadeType.ALL])
    lateinit var address: OrderAddressEntity
}