package dev.luciano.ordersphere.infrastructure.messaging

import com.fasterxml.jackson.databind.ObjectMapper
import dev.luciano.ordersphere.application.service.create.OrderCreationSagaOrchestrator
import dev.luciano.ordersphere.configuration.logger.CompanionLogger
import dev.luciano.ordersphere.domain.event.PaymentCompletedEvent
import dev.luciano.ordersphere.domain.event.PaymentFailedEvent
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class PaymentProcessingListener(
    private val orchestrator: OrderCreationSagaOrchestrator,
) {
    companion object : CompanionLogger()

    @KafkaListener(
        topics = ["\${kafka.event.topic.order.payment-completed}"],
        groupId = "\${kafka.event.group.order-processing}"
    )
    fun listen(event: PaymentCompletedEvent) {
        log { info("Received payment completed event: {}", event) }
        orchestrator.orchestrate(event)
    }

    @KafkaListener(
        topics = ["\${kafka.event.topic.order.payment-failed}"],
        groupId = "\${kafka.event.group.order-processing}"
    )
    fun listen(event: PaymentFailedEvent) {
        log { info("Received payment failed event: {}", event) }
        orchestrator.orchestrate(event)
    }
}