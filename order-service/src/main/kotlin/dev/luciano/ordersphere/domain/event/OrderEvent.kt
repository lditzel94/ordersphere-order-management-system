package dev.luciano.ordersphere.domain.event

import java.time.ZonedDateTime

sealed class OrderEvent(open val createdAt: ZonedDateTime)