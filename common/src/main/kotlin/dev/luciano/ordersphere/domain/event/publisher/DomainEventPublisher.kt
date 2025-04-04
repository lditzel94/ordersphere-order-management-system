package dev.luciano.ordersphere.domain.event.publisher

import dev.luciano.ordersphere.domain.event.DomainEvent


interface DomainEventPublisher<T : DomainEvent<T>> {
    fun publish(domainEvent: T)
}