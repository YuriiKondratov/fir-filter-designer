package core

import kotlin.math.ceil
import kotlin.math.floor

fun designLowPassFilter(
    sampleRate: Int,
    lowPassFrequency: Double,
    numberOfTaps: Int,
    windowFunction: WindowFunction
): Filter {
    val f = lowPassFrequency / sampleRate
    val coefficients = (0 until numberOfTaps).map { n ->
        sinc(2 * f * (n - (numberOfTaps - 1) / 2))
    }
    return Filter(coefficients)
        .windowedBy(windowFunction)
        .normalized()
}

fun designHighPassFilter(
    sampleRate: Int,
    highPassFrequency: Double,
    numberOfTaps: Int,
    windowFunction: WindowFunction
): Filter {
    val coefficients = designLowPassFilter(sampleRate, highPassFrequency, numberOfTaps, windowFunction)
        .coefficients
        .map { it * -1 }
        .toMutableList()
    val mid = coefficients[numberOfTaps / 2]
    coefficients[numberOfTaps / 2] = mid + 1
    return Filter(coefficients)
}
//
//fun designBandPassFilter(
//    cutoffFrequencyLow: Double,
//    cutoffFrequencyHigh: Double,
//    sampleRate: Int,
//    numberOfTaps: Int,
//    windowFunction: WindowFunction
//): Filter {
//    val lowPass = designLowPassFilter(
//        cutoffFrequencyHigh,
//        sampleRate,
//        floor(numberOfTaps / 2.0).toInt() + 1,
//        windowFunction
//    )
//    val highPass = designHighPassFilter(
//        cutoffFrequencyLow,
//        sampleRate, ceil(numberOfTaps / 2.0).toInt(),
//        windowFunction
//    )
//
//    return Filter(
//        _convolution(lowPass.coefficients, highPass.coefficients)
//    )
//}
//
//fun designBandRejectFilter(
//    cutoffFrequencyLow: Double,
//    cutoffFrequencyHigh: Double,
//    sampleRate: Int,
//    numberOfTaps: Int,
//    windowFunction: WindowFunction
//): Filter {
//    val coefficients = designBandPassFilter(
//        cutoffFrequencyLow,
//        cutoffFrequencyHigh,
//        sampleRate,
//        numberOfTaps,
//        windowFunction
//    )
//        .coefficients
//        .map { it * -1 }
//        .toMutableList()
//    val mid = coefficients[numberOfTaps / 2]
//    coefficients[numberOfTaps / 2] = mid + 1
//    return Filter(coefficients)
//}
