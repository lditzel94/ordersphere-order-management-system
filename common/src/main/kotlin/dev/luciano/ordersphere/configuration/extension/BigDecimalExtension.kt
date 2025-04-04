package dev.luciano.ordersphere.configuration.extension

import java.math.BigDecimal
import java.math.RoundingMode

infix fun BigDecimal.setScale(scale: Int): BigDecimal =
    setScale(scale, RoundingMode.HALF_EVEN)