package dev.luciano.food.ordering.system.common.domain.entity

abstract class BaseEntity<ID>(protected val id: ID) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BaseEntity<*>

        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()
}