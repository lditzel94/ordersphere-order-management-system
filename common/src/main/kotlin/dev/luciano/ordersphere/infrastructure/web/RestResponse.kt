package dev.luciano.ordersphere.infrastructure.web

import java.time.Instant
import java.util.UUID

sealed class RestResponse

data class SuccessResponse<R>(
    val data: R,
    val meta: MetaInfo = MetaInfo(),
) : RestResponse()

data class FailureResponse(
    val message: String,
    val errorCode: String,
    val meta: MetaInfo = MetaInfo(),
) : RestResponse()

data class ValidationErrorResponse(
    val message: String,
    val errorCode: String,
    val fieldErrors: List<FieldError>,
) : RestResponse() {
    val timestamp: String = Instant.now().toString()

    data class FieldError(
        val field: String,
        val message: String,
    ) {
        override fun toString(): String {
            return "FieldError(field='$field', message='$message')"
        }
    }
}

data class MetaInfo(val timestamp: Instant = Instant.now())
