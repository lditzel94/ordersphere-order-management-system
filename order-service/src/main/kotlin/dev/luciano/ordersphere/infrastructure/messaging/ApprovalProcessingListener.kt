package dev.luciano.ordersphere.infrastructure.messaging

import com.fasterxml.jackson.databind.ObjectMapper
import dev.luciano.ordersphere.application.service.create.OrderCreationSagaOrchestrator
import dev.luciano.ordersphere.configuration.logger.CompanionLogger
import dev.luciano.ordersphere.domain.event.ApprovalAcceptedEvent
import dev.luciano.ordersphere.domain.event.ApprovalEvent
import dev.luciano.ordersphere.domain.event.ApprovalRejectedEvent
import dev.luciano.ordersphere.domain.event.PaymentEvent
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class ApprovalProcessingListener(
    private val orchestrator: OrderCreationSagaOrchestrator,
    private val objectMapper: ObjectMapper,
) {
    companion object : CompanionLogger()

    @KafkaListener(
        topics = [
            "\${kafka.event.topic.order.approval-accepted}",
        ],
        groupId = "\${kafka.event.group.order-processing}"
    )
    fun listen(message: ApprovalAcceptedEvent) {
        log { info("Received approval event: {}", message) }
        orchestrator.orchestrate(message)
    }

    @KafkaListener(
        topics = [
            "\${kafka.event.topic.order.approval-rejected}",
        ],
        groupId = "\${kafka.event.group.order-processing}"
    )
    fun listen(message: ApprovalRejectedEvent) {
        log { info("Received approval event: {}", message) }
        orchestrator.orchestrate(message)
    }
}