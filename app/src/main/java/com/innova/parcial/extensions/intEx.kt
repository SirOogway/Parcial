package com.innova.parcial.extensions

fun Int.isPrimeNumber(): Boolean {
    val lastDivider: Int = Math.floorDiv(this, 2)

    for (divider in 2..lastDivider) {
        if (this % divider == 0) return false
    }
    return true
}