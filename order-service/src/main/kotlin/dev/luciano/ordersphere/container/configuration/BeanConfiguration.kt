package dev.luciano.ordersphere.container.configuration

import dev.luciano.ordersphere.domain.service.OrderApprovalDomainService
import dev.luciano.ordersphere.domain.service.OrderApprovalService
import dev.luciano.ordersphere.domain.service.OrderCancellationDomainService
import dev.luciano.ordersphere.domain.service.OrderCancellationService
import dev.luciano.ordersphere.domain.service.OrderCreationDomainService
import dev.luciano.ordersphere.domain.service.OrderCreationService
import dev.luciano.ordersphere.domain.service.OrderPaymentCancellationDomainService
import dev.luciano.ordersphere.domain.service.OrderPaymentCancellationService
import dev.luciano.ordersphere.domain.service.OrderPaymentDomainService
import dev.luciano.ordersphere.domain.service.OrderPaymentService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class BeanConfiguration {

    @Bean
    open fun orderCreationService(): OrderCreationService = OrderCreationDomainService()

    @Bean
    open fun payOrderService(): OrderPaymentService =
        OrderPaymentDomainService()

    @Bean
    open fun approveOrderService(): OrderApprovalService = OrderApprovalDomainService()

    @Bean
    open fun cancelOrderPaymentService(): OrderPaymentCancellationService = OrderPaymentCancellationDomainService()

    @Bean
    open fun cancelOrderService(): OrderCancellationService = OrderCancellationDomainService()
}