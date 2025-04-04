package dev.luciano.ordersphere.domain.valueobject

import dev.luciano.ordersphere.configuration.extension.setScale
import java.math.BigDecimal

data class Money(val amount: BigDecimal) {
    companion object {
        val ZERO = Money(BigDecimal.ZERO)
    }

    val isGreaterThanZero: Boolean
        get() = amount > BigDecimal.ZERO

    fun isGreaterThan(money: Money): Boolean = amount > money.amount

    operator fun plus(money: Money): Money =
        Money(amount.add(money.amount) setScale 2)

    operator fun minus(money: Money): Money =
        Money(amount.subtract(money.amount) setScale 2)

    operator fun times(multiplier: Int): Money =
        Money(amount.multiply(BigDecimal(multiplier)) setScale 2)
}