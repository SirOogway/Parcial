package com.innova.parcial.extensions
import kotlin.math.floor
import kotlin.math.pow

fun Double.toFraction(): String {
    val denominator = this.generateDenominator()
    val numerator = (this * denominator).toInt()
    val mcd = mcd(numerator, denominator)

    return "${simplify(numerator, mcd)} / ${simplify(denominator, mcd)}"
}

fun Double.generateDenominator(): Int {
    return if (this != floor(this)) 10.0.pow(this.countDecimals().toDouble()).toInt()
    else 1
}

fun Double.countDecimals() = this.toString().split('.').component2().length

fun mcd(numerator: Int, denominator: Int): Int {
    var newNumerator = numerator
    var newDenominator = denominator
    var primeNumber = 2
    var mcd = 1

    while (primeNumber <= newNumerator && primeNumber <= newDenominator) {
        if (newNumerator % primeNumber == 0 && newDenominator % primeNumber == 0) {
            newNumerator /= primeNumber
            newDenominator /= primeNumber
            mcd *= primeNumber
        } else {
            primeNumber = nextPrimeNumber(primeNumber)
        }
    }
    return mcd
}

fun simplify(a: Int, mcd: Int) = a / mcd

fun nextPrimeNumber(a: Int): Int {
    var i = a + 1
    while (!i.isPrimeNumber()) ++i
    return i
}

fun Double.roundDecimals() = (this * 100000).toInt() / 100000.0