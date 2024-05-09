package core

import kotlin.math.log10

fun List<Double>.frequencyDomain(sampleRate: Int): Map<Double, Double> {

    //padding
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

fun List<Double>.frequencyDomainDb(sampleRate: Int): Map<Double, Double> {
    val frequencyResp = frequencyDomain(sampleRate)
    return frequencyResp.entries.associate {
        it.key to 20 * log10(it.value)
    }
}

fun Map<Double, Double>.applyFilter(filter: List<Double>): Map<Double, Double> {
    val conv = convolution(values.toList(), filter)
    val indent = filter.size / 2
    val sameConv = conv.subList(indent, conv.size - indent)

    return sameConv.zip(keys)
        .subList(indent, sameConv.size - indent)
        .associate { (x, t) -> t to x }
}

private const val FREQUENCY_RESPONSE_POINTS = 1024
