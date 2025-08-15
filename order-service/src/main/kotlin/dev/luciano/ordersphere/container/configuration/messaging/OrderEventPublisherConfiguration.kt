package dev.luciano.ordersphere.container.configuration.messaging

import dev.luciano.ordersphere.application.port.output.message.publisher.OrderCreatedEventPublisher
import dev.luciano.ordersphere.domain.error.OrderError
import dev.luciano.ordersphere.domain.event.OrderCreatedEvent
import dev.luciano.ordersphere.domain.event.OrderEvent
import dev.luciano.ordersphere.infrastructure.messaging.KafkaProducer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class OrderEventPublisherConfiguration(
    private val kafkaProducer: KafkaProducer,
    private val orderTopics: KafkaTopicConfiguration.OrderTopics,
) {
    @Bean
    open fun orderCreatedEventPublisher() = OrderCreatedEventPublisher {
        kafkaProducer.send<OrderCreatedEvent, OrderError>(
            key = it.orderId.toString(),
            event = it,
            topic = orderTopics.created
        )
    }
}