package dev.luciano.ordersphere.container.configuration.messaging

import dev.luciano.ordersphere.application.port.output.message.publisher.OrderCreatedEventPublisher
import dev.luciano.ordersphere.domain.event.OrderEvent
import dev.luciano.ordersphere.infrastructure.messaging.KafkaProducer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OrderEventPublisherConfiguration(
    private val kafkaProducer: KafkaProducer<OrderEvent>,
    private val orderTopics: KafkaTopicConfiguration.OrderTopics,
) {
    @Bean
    fun orderCreatedEventPublisher() = OrderCreatedEventPublisher {
        kafkaProducer.send(
            key = it.orderId.toString(),
            event = it,
            topic = orderTopics.created
        )
    }
}