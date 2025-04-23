package dev.luciano.ordersphere.application.port.output.message.publisher

import arrow.core.Either
import dev.luciano.ordersphere.domain.error.OrderError
import dev.luciano.ordersphere.domain.event.OrderCreatedEvent
import dev.luciano.ordersphere.domain.event.OrderEvent

fun interface OrderCreatedEventPublisher {
    suspend fun publish(event: OrderCreatedEvent): Either<OrderError, OrderEvent>
}