package dev.luciano.ordersphere.infrastructure.saga

enum class SagaStatus {
    STARTED,
    FAILED,
    SUCCEEDED,
    PROCESSING,
    COMPENSATING,
    COMPENSATED;
}