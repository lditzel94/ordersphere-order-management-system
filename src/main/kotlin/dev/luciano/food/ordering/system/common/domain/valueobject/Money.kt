package dev.luciano.food.ordering.system.common.domain.valueobject

import dev.luciano.food.ordering.system.common.configuration.extensions.setScale
import java.math.BigDecimal

data class Money(val amount: BigDecimal) {
    companion object {
        val ZERO = Money(BigDecimal.ZERO)
    }

    val isGreaterThanZero: Boolean
        get() = amount > BigDecimal.ZERO

    fun isGreaterThan(money: Money): Boolean = amount > money.amount

    operator fun plus(money: Money): Money = Money(amount.add(money.amount) setScale 2)

    operator fun minus(money: Money): Money = Money(amount.subtract(money.amount) setScale 2)

    operator fun times(multiplier: Int): Money = Money(amount.multiply(BigDecimal(multiplier)) setScale 2)
}