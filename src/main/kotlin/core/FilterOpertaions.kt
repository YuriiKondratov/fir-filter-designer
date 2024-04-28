package core

import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.log10

private const val FREQUENCY_RESPONSE_POINTS = 1024

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

fun Filter.frequencyResponse(sampleRate: Int): Map<Double, Double> {
    //padding
    val dftInput = coefficients.toMutableList()
    while (dftInput.size < FREQUENCY_RESPONSE_POINTS) {
        dftInput.add(0.0)
    }

    val dftOutput = dft(dftInput)

    val binSize = sampleRate.toDouble() / dftOutput.size
    return dftOutput.withIndex()
        .associate { (i, x) ->
            i * binSize to x.abs()
        }.filter {
            it.key < sampleRate / 2.0
        }
}

fun Filter.frequencyResponseDb(sampleRate: Int): Map<Double, Double> {
    val frequencyResp = frequencyResponse(sampleRate)
    return frequencyResp.entries.associate {
        it.key to 20 * log10(it.value)
    }
}
