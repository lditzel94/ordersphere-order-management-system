package dev.luciano.ordersphere.infrastructure.web

import dev.luciano.ordersphere.configuration.logger.CompanionLogger
import jakarta.validation.ConstraintViolation
import jakarta.validation.ConstraintViolationException
import jakarta.validation.ValidationException
import java.util.stream.Collectors
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalExceptionHandler {
    companion object : CompanionLogger()

    @ResponseBody
    @ExceptionHandler(value = [Exception::class])
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(exception: Exception): FailureResponse =
        FailureResponse(
            errorCode = HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
            message = "Unexpected error"
        ).log { error("Error: {}, {}", exception.message, exception) }

    @ResponseBody
    @ExceptionHandler(value = [ValidationException::class])
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleException(validationException: ValidationException): FailureResponse {
        val error: FailureResponse
        if (validationException is ConstraintViolationException) {
            val violations = extractViolationsFromException(validationException)
            error = FailureResponse(
                errorCode = HttpStatus.BAD_REQUEST.reasonPhrase,
                message = violations,
            ).log { error(violations, validationException) }
        } else {
            val exceptionMessage = validationException.message
            error = FailureResponse(
                errorCode = HttpStatus.BAD_REQUEST.reasonPhrase,
                message = exceptionMessage!!,
            ).log { error(exceptionMessage!!, validationException) }
        }
        return error
    }

    private fun extractViolationsFromException(validationException: ConstraintViolationException): String {
        return validationException.constraintViolations
            .stream()
            .map { obj: ConstraintViolation<*> -> obj.message }
            .collect(Collectors.joining("--"))
    }
}