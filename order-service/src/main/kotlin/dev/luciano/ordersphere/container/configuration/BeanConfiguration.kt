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
class BeanConfiguration {

    @Bean
    fun orderCreationService(): OrderCreationService = OrderCreationDomainService()

    @Bean
    fun payOrderService(): OrderPaymentService =
        OrderPaymentDomainService()

    @Bean
    fun approveOrderService(): OrderApprovalService = OrderApprovalDomainService()

    @Bean
    fun cancelOrderPaymentService(): OrderPaymentCancellationService = OrderPaymentCancellationDomainService()

    @Bean
    fun cancelOrderService(): OrderCancellationService = OrderCancellationDomainService()
}