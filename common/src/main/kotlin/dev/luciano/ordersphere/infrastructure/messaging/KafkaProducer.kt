package dev.luciano.ordersphere.infrastructure.messaging

import arrow.core.Either
import arrow.core.raise.catch
import arrow.core.raise.either
import dev.luciano.ordersphere.configuration.logger.CompanionLogger
import dev.luciano.ordersphere.domain.error.Error
import jakarta.annotation.PreDestroy
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaProducer(val kafkaTemplate: KafkaTemplate<String, Any>) {
    companion object : CompanionLogger()

    final suspend inline fun <reified T : Any, E : Error> send(
        key: String,
        event: T,
        topic: String,
    ): Either<E, T> = either {
        catch({
            kafkaTemplate.send(topic, key, event)
            return@catch event
        }) { ex ->
            log { error("Exception encountered on Kafka Producer: {}", ex.message, ex) }
            raise(Error { ex.localizedMessage } as E)
        }
    }.logEither(
        left = { error("Error producing message: {}", it.message()) },
        right = { info("Topic {} produced with message:{}", topic, event) }
    )

    @PreDestroy
    fun close() = kafkaTemplate.destroy()
}