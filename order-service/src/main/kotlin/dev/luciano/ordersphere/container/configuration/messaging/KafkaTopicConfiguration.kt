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
open class KafkaTopicConfiguration(
    @Value("\${kafka.topic.replicas}")
    val replicas: Int,
    @Value("\${kafka.topic.partitions}")
    val partitions: Int,
) {

    @ConfigurationProperties(prefix = "kafka.event.topic.order")
    data class OrderTopics(
        val created: String,
        val paymentCompleted: String,
        val paymentFailed: String,
        val approvalAccepted: String,
        val approvalRejected: String,
    )

    @Bean
    open fun topics(orderTopics: OrderTopics) = KafkaAdmin.NewTopics(
        TopicBuilder.name(orderTopics.created)
            .partitions(partitions)
            .replicas(replicas)
            .build(),
        TopicBuilder.name(orderTopics.paymentCompleted)
            .partitions(partitions)
            .replicas(replicas)
            .build(),
        TopicBuilder.name(orderTopics.paymentFailed)
            .partitions(partitions)
            .replicas(replicas)
            .build(),
        TopicBuilder.name(orderTopics.approvalAccepted)
            .partitions(partitions)
            .replicas(replicas)
            .build(),
        TopicBuilder.name(orderTopics.approvalRejected)
            .partitions(partitions)
            .replicas(replicas)
            .build(),
    )
}