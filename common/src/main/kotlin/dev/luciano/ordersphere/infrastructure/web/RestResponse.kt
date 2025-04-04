package dev.luciano.ordersphere.infrastructure.web

import java.time.Instant

sealed class RestResponse

data class SuccessResponse<R>(val data: R) : RestResponse()
data class FailureResponse(
    val message: String,
    val errorCode: String,
) : RestResponse() {
    val timestamp: String = Instant.now().toString()
}
