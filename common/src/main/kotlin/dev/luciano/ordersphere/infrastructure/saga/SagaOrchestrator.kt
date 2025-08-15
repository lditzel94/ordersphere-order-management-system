package dev.luciano.ordersphere.infrastructure.saga

import arrow.core.Either

interface SagaOrchestrator<T, E> {
    suspend fun <I : T> start(input: I): Either<E, T>
}