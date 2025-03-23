package dev.luciano.food.ordering.system.common.configuration.extensions

import java.math.BigDecimal
import java.math.RoundingMode

infix fun BigDecimal.setScale(scale: Int): BigDecimal =
    setScale(scale, RoundingMode.HALF_EVEN)