package dev.luciano.food.ordering.system.common.domain.event.publisher

import dev.luciano.food.ordering.system.common.domain.event.DomainEvent

interface DomainEventPublisher<T: DomainEvent<T>>{
    fun publish(domainEvent: T)
}