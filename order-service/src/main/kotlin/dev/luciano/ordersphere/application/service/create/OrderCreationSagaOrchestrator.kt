package dev.luciano.ordersphere.application.service.create

import arrow.core.Either
import dev.luciano.ordersphere.application.port.input.service.OrderApprovalSagaStep
import dev.luciano.ordersphere.application.port.input.service.OrderPaymentSagaStep
import dev.luciano.ordersphere.application.port.output.message.publisher.OrderCreatedEventPublisher
import dev.luciano.ordersphere.configuration.logger.CompanionLogger
import dev.luciano.ordersphere.domain.error.OrderError
import dev.luciano.ordersphere.domain.event.ApprovalAcceptedEvent
import dev.luciano.ordersphere.domain.event.ApprovalEvent
import dev.luciano.ordersphere.domain.event.ApprovalRejectedEvent
import dev.luciano.ordersphere.domain.event.OrderCreatedEvent
import dev.luciano.ordersphere.domain.event.OrderEvent
import dev.luciano.ordersphere.domain.event.PaymentCompletedEvent
import dev.luciano.ordersphere.domain.event.PaymentEvent
import dev.luciano.ordersphere.domain.event.PaymentFailedEvent
import org.springframework.stereotype.Service

@Service
class OrderCreationSagaOrchestrator(
    private val orderPayment: OrderPaymentSagaStep,
    private val orderApproval: OrderApprovalSagaStep,
    private val publisher: OrderCreatedEventPublisher
) {
    companion object : CompanionLogger()

    suspend fun start(event: OrderCreatedEvent): Either<OrderError, OrderEvent> =
        publisher.publish(event)
            .logEither(
                { error("Failed to publish order created event: $it") },
                { info("Order created event published successfully") }
            )


    fun orchestrate(event: PaymentEvent) = when (event) {
        is PaymentCompletedEvent -> orderPayment.process(event)
        is PaymentFailedEvent -> orderPayment.rollback(event)
    }

    fun orchestrate(event: ApprovalEvent) = when (event) {
        is ApprovalAcceptedEvent -> orderApproval.process(event)
        is ApprovalRejectedEvent -> orderApproval.rollback(event)
    }
}