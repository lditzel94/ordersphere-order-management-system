package dev.luciano.ordersphere.application.usecase

import arrow.core.Either
import arrow.core.raise.Raise
import arrow.core.raise.catch
import arrow.core.raise.either
import dev.luciano.ordersphere.application.dto.create.CreateOrderCommand
import dev.luciano.ordersphere.application.mapper.createOrderCommandToOrder
import dev.luciano.ordersphere.application.port.output.repository.CustomerRepository
import dev.luciano.ordersphere.application.port.output.repository.OrderRepository
import dev.luciano.ordersphere.application.port.output.repository.RestaurantRepository
import dev.luciano.ordersphere.configuration.logger.CompanionLogger
import dev.luciano.ordersphere.domain.entity.Customer
import dev.luciano.ordersphere.domain.entity.Restaurant
import dev.luciano.ordersphere.domain.entity.order.OpenOrder
import dev.luciano.ordersphere.domain.error.OrderDomainError
import dev.luciano.ordersphere.domain.error.OrderError
import dev.luciano.ordersphere.domain.event.OrderCreatedEvent
import dev.luciano.ordersphere.domain.service.OrderCreationService
import dev.luciano.ordersphere.domain.valueobject.CustomerId
import dev.luciano.ordersphere.domain.valueobject.ProductId
import dev.luciano.ordersphere.domain.valueobject.RestaurantId
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

fun interface CreateOrder {
    suspend operator fun invoke(createOrderCommand: CreateOrderCommand): Either<OrderError, OrderCreatedEvent>
}

@Component
open class CreateOrderUseCase(
    private val orderCreationService: OrderCreationService,
    private val orderRepository: OrderRepository,
    private val customerRepository: CustomerRepository,
    private val restaurantRepository: RestaurantRepository,
) : CreateOrder {
    companion object : CompanionLogger()

    @Transactional
    override suspend fun invoke(createOrderCommand: CreateOrderCommand): Either<OrderError, OrderCreatedEvent> = either {
        log { info("Creating order for customerId={} with restaurantId={}", createOrderCommand.customerId, createOrderCommand.restaurantId) }

        ensureCustomerExists(createOrderCommand)
        ensureRestaurantExists(createOrderCommand)
            .let { restaurant -> createOrder(createOrderCommand, restaurant) }
            .log { info("Order created with id={}", it.orderId) }
    }

    private fun Raise<OrderError>.ensureCustomerExists(createOrderCommand: CreateOrderCommand): Customer =
        customerRepository.findBy(CustomerId(createOrderCommand.customerId))
            ?: raise(OrderDomainError("Customer with id=${createOrderCommand.customerId} does not exist"))

    private fun Raise<OrderError>.ensureRestaurantExists(createOrderCommand: CreateOrderCommand): Restaurant {
        val restaurantId = RestaurantId(createOrderCommand.restaurantId)
        val productIds = createOrderCommand.items.map { ProductId(it.productId) }

        return restaurantRepository.findRestaurantInformation(restaurantId, productIds)
            ?: raise(OrderDomainError("Restaurant with id=${createOrderCommand.restaurantId} does not exist"))
    }

    private suspend fun Raise<OrderError>.createOrder(command: CreateOrderCommand, restaurant: Restaurant) = catch({
        createOrderCommandToOrder.map(command).let { order ->
            orderCreationService(order as OpenOrder, restaurant)
                .onRight { orderRepository.save(order) }
                .bind()
        }
    }) { raise(OrderDomainError(it.localizedMessage)) }
}