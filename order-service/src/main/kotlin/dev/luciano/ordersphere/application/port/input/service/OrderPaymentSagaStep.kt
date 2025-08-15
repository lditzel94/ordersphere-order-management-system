package dev.luciano.ordersphere.application.port.input.service

import dev.luciano.ordersphere.domain.event.OrderPaidEvent
import dev.luciano.ordersphere.domain.event.PaymentCompletedEvent
import dev.luciano.ordersphere.domain.event.PaymentEvent
import dev.luciano.ordersphere.infrastructure.saga.SagaStep

interface OrderPaymentSagaStep : SagaStep<PaymentEvent>