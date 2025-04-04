package dev.luciano.ordersphere.infrastructure.persistence.order.adapter

import dev.luciano.ordersphere.application.port.output.repository.OrderRepository
import dev.luciano.ordersphere.configuration.logger.CompanionLogger
import dev.luciano.ordersphere.domain.entity.Order
import dev.luciano.ordersphere.domain.valueobject.OrderId
import dev.luciano.ordersphere.domain.valueobject.TrackingId
import dev.luciano.ordersphere.infrastructure.persistence.order.mapper.orderEntityToOrder
import dev.luciano.ordersphere.infrastructure.persistence.order.mapper.orderToOrderEntity
import dev.luciano.ordersphere.infrastructure.persistence.order.repository.OrderJpaRepository
import org.springframework.stereotype.Component

@Component
class OrderRepositoryAdapter(
    private val orderJpaRepository: OrderJpaRepository,
) : OrderRepository {
    companion object : CompanionLogger()

    override fun save(order: Order): Order =
        orderJpaRepository.save(orderToOrderEntity.map(order)).let {
            orderEntityToOrder.map(it)
        }

    override fun findBy(orderId: OrderId): Order? =
        orderJpaRepository.findById(orderId.value)
            .map(orderEntityToOrder::map)
            .get()

    override fun findBy(trackingId: TrackingId): Order? =
        orderJpaRepository.findByTrackingId(trackingId.value)
            ?.let { orderEntityToOrder.map(it) }
}