package dev.luciano.ordersphere.application.service.create

import dev.luciano.ordersphere.application.port.input.service.OrderApprovalSagaStep
import dev.luciano.ordersphere.configuration.logger.CompanionLogger
import dev.luciano.ordersphere.domain.event.ApprovalAcceptedEvent
import dev.luciano.ordersphere.domain.event.ApprovalEvent
import org.springframework.stereotype.Service

@Service
class OrderApprovalSagaStepService : OrderApprovalSagaStep {
    companion object : CompanionLogger()

    override fun process(data: ApprovalEvent) {
        TODO("Not yet implemented")
        TODO("Update order to approved")
        log { info("Order approved") }
    }

    override fun rollback(data: ApprovalEvent) {
        TODO("Not yet implemented")
        TODO("Update order to cancelled and compensate the payment")
        log { info("Order rejected") }
    }
}