package dev.luciano.ordersphere.infrastructure.web.rest

import dev.luciano.ordersphere.application.dto.create.CreateOrderCommand
import dev.luciano.ordersphere.application.port.input.service.OrderService
import dev.luciano.ordersphere.configuration.logger.CompanionLogger
import dev.luciano.ordersphere.infrastructure.web.FailureResponse
import dev.luciano.ordersphere.infrastructure.web.RestResponse
import dev.luciano.ordersphere.infrastructure.web.SuccessResponse
import org.springframework.http.HttpStatus.CONFLICT
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/orders"], produces = ["application/vnd.api.v1+json"])
class OrderController(
    private val orderService: OrderService,
) {
    companion object : CompanionLogger()

    @PostMapping
    suspend fun createOrder(
        @RequestBody
        order: CreateOrderCommand
    ): ResponseEntity<RestResponse> =
        orderService.createOrder(order)
            .fold(
                ifLeft = { error ->
                    log { error(error.message) }
                    ResponseEntity.status(CONFLICT)
                        .body(FailureResponse(message = error.message, errorCode = CONFLICT.reasonPhrase))
                },
                ifRight = { orderResponse ->
                    log { info("Order created with tracking id: {}", orderResponse.orderTrackingId) }
                    ResponseEntity.ok(SuccessResponse(orderResponse))
                }
            )
}