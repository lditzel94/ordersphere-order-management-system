package dev.luciano.ordersphere.infrastructure.persistence.order.adapter

import dev.luciano.ordersphere.application.port.output.repository.OrderRepository
import dev.luciano.ordersphere.configuration.logger.CompanionLogger
import dev.luciano.ordersphere.domain.entity.Order
import dev.luciano.ordersphere.domain.valueobject.OrderId
import dev.luciano.ordersphere.domain.valueobject.TrackingId
import dev.luciano.ordersphere.infrastructure.persistence.order.mapper.orderEntityToOrder
import dev.luciano.ordersphere.infrastructure.persistence.order.mapper.orderToOrderEntity
import dev.luciano.ordersphere.infrastructure.persistence.order.repository.OrderJpaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component

@Component
class OrderRepositoryAdapter(
    private val orderJpaRepository: OrderJpaRepository,
) : OrderRepository {
    companion object : CompanionLogger()

    override suspend fun save(order: Order): Order = withContext(Dispatchers.IO) {
        orderJpaRepository
            .save(orderToOrderEntity.map(order))
            .let(orderEntityToOrder::map)
    }


    override suspend fun findBy(orderId: OrderId): Order? = withContext(Dispatchers.IO) {
        orderJpaRepository.findById(orderId.value)
            .map(orderEntityToOrder::map)
            .get()
    }

    override suspend fun findBy(trackingId: TrackingId): Order? =
        orderJpaRepository.findByTrackingId(trackingId.value)
            .map(orderEntityToOrder::map)
            .get()
}