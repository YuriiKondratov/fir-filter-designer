package core

import kotlin.math.log10

fun List<Double>.frequencyResponse(sampleRate: Int): Map<Double, Double> {
    val dftInput = this.toMutableList()
    while (dftInput.size < FREQUENCY_RESPONSE_POINTS) {
        dftInput.add(0.0)
    }

    val dftOutput = dft(dftInput.subList(0, FREQUENCY_RESPONSE_POINTS))

    val binSize = sampleRate.toDouble() / dftOutput.size
    return dftOutput.withIndex()
        .associate { (i, x) ->
            i * binSize to x.abs()
        }.filter {
            it.key < sampleRate / 2.0
        }
}

fun List<Double>.frequencyResponseDb(sampleRate: Int): Map<Double, Double> {
    val frequencyResp = frequencyResponse(sampleRate)
    return frequencyResp.entries.associate {
        it.key to 20 * log10(it.value)
    }
}

const val FREQUENCY_RESPONSE_POINTS = 2048
