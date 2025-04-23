package dev.luciano.ordersphere.container.configuration.messaging

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.KafkaAdmin

@Configuration
@ConfigurationPropertiesScan
class KafkaTopicConfiguration(
    @Value("\${kafka.topic.replicas}")
    val replicas: Int,
    @Value("\${kafka.topic.partitions}")
    val partitions: Int,
) {

    @ConfigurationProperties(prefix = "kafka.event.topic.order")
    data class OrderTopics(val created: String)

    @Bean
    fun topics(orderTopics: OrderTopics) = KafkaAdmin.NewTopics(
        TopicBuilder.name(orderTopics.created)
            .partitions(partitions)
            .replicas(replicas)
            .build()
    )
}