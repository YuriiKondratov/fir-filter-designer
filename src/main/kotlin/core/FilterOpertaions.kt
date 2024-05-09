package core

import kotlin.math.ceil
import kotlin.math.floor

fun Filter.windowedBy(window: WindowFunction) =
    Filter(coefficients.zip(window()).map { (x, w) -> x * w})

fun Filter.normalized() =
    with(coefficients.sum()) {
        Filter(coefficients.map { it / this })
    }

fun Filter.impulseResponse(): Map<Double, Double> {
    val mid = coefficients.size / 2.0
    return (-floor(mid).toInt() until ceil(mid).toInt())
        .withIndex()
        .associate { (i, x) ->
            x.toDouble() to coefficients[i]
        }
}
