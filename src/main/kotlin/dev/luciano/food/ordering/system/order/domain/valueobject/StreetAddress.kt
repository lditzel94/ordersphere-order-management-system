package dev.luciano.food.ordering.system.order.domain.valueobject

import java.util.UUID

data class StreetAddress(
    val id: UUID,
    val street: String,
    val postalCode: String,
    val city: String
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as StreetAddress
        return street == other.street && postalCode == other.postalCode && city == other.city
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
