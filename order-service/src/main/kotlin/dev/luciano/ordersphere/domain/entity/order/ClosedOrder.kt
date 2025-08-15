package dev.luciano.ordersphere.domain.entity.order

import dev.luciano.ordersphere.domain.valueobject.CustomerId
import dev.luciano.ordersphere.domain.valueobject.Money
import dev.luciano.ordersphere.domain.valueobject.OrderId
import dev.luciano.ordersphere.domain.valueobject.OrderState

data class ClosedOrder(
    override val orderId: OrderId,
    override val state: OrderState,
    override val price: Money,
    override val customerId: CustomerId,
    val cancellationReason: String,
) : Order
