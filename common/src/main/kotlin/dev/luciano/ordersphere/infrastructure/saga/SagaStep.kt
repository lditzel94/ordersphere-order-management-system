package dev.luciano.ordersphere.infrastructure.saga

interface SagaStep<T> {
    fun process(data: T)
    fun rollback(data: T)
}