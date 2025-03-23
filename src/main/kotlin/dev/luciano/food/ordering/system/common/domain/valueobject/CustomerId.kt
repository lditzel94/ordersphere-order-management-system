package dev.luciano.food.ordering.system.common.domain.valueobject

import org.hibernate.validator.constraints.UUID

@JvmInline
value class CustomerId(val value: UUID)