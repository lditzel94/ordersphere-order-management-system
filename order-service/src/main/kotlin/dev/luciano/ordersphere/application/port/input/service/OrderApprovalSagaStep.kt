package dev.luciano.ordersphere.application.port.input.service

import dev.luciano.ordersphere.domain.event.ApprovalEvent
import dev.luciano.ordersphere.infrastructure.saga.SagaStep

interface OrderApprovalSagaStep : SagaStep<ApprovalEvent>