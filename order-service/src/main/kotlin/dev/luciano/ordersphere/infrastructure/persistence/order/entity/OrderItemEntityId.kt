package dev.luciano.ordersphere.infrastructure.persistence.order.entity

import java.io.Serializable

data class OrderItemEntityId(
    val id: Long,
    val order: OrderEntity,
) : Serializable