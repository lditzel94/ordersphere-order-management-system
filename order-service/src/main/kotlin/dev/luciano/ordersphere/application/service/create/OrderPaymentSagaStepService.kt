package dev.luciano.ordersphere.application.service.create

import dev.luciano.ordersphere.application.port.input.service.OrderPaymentSagaStep
import dev.luciano.ordersphere.configuration.logger.CompanionLogger
import dev.luciano.ordersphere.domain.event.PaymentEvent
import org.springframework.stereotype.Service

@Service
class OrderPaymentSagaStepService : OrderPaymentSagaStep {
    companion object : CompanionLogger()

    override fun process(data: PaymentEvent) {
        TODO("Not yet implemented")
        TODO("Update the order status to PAID")
        log { info("Order paid") }
    }

    override fun rollback(data: PaymentEvent) {
        TODO("Not yet implemented")
        TODO("Update the order status to CANCELLED")
        log { info("Order payment failed, rolling back") }
    }
}