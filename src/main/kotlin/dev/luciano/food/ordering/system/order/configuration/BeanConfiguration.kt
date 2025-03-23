package dev.luciano.food.ordering.system.order.configuration

import dev.luciano.food.ordering.system.order.domain.service.ApproveOrderDomainService
import dev.luciano.food.ordering.system.order.domain.service.ApproveOrderService
import dev.luciano.food.ordering.system.order.domain.service.CancelOrderDomainService
import dev.luciano.food.ordering.system.order.domain.service.CancelOrderPaymentDomainService
import dev.luciano.food.ordering.system.order.domain.service.CancelOrderPaymentService
import dev.luciano.food.ordering.system.order.domain.service.CancelOrderService
import dev.luciano.food.ordering.system.order.domain.service.OrderCreationDomainService
import dev.luciano.food.ordering.system.order.domain.service.OrderCreationService
import dev.luciano.food.ordering.system.order.domain.service.PayOrderDomainService
import dev.luciano.food.ordering.system.order.domain.service.PayOrderService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfiguration {

    @Bean
    fun orderCreationService(): OrderCreationService = OrderCreationDomainService()

    @Bean
    fun payOrderService(): PayOrderService = PayOrderDomainService()

    @Bean
    fun approveOrderService(): ApproveOrderService = ApproveOrderDomainService()

    @Bean
    fun cancelOrderPaymentService(): CancelOrderPaymentService = CancelOrderPaymentDomainService()

    @Bean
    fun cancelOrderService(): CancelOrderService = CancelOrderDomainService()
}