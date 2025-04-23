package dev.luciano.ordersphere.infrastructure.messaging

import dev.luciano.ordersphere.configuration.logger.CompanionLogger
import dev.luciano.ordersphere.domain.event.OrderCreatedEvent
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class OrderCreatedEventListener {
    companion object : CompanionLogger()

    @KafkaListener(
        topics = ["\${kafka.event.topic.order.created}"],
        groupId = "order.processing.group",
    )
    fun listen(message: OrderCreatedEvent) {
        log { info("Order created event consumed: {}", message) }
    }
}