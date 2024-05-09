package core

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

fun designBandPassFilter(
    lowPassFrequency: Double,
    highPassFrequency: Double,
    lowPassNumberOfTaps: Int,
    highPassNumberOfTaps: Int,
    lowPassWindow: WindowFunction,
    highPassWindow: WindowFunction,
    sampleRate: Int,
): Filter {
    val lowPass = designLowPassFilter(
        sampleRate,
        highPassFrequency,
        lowPassNumberOfTaps,
        lowPassWindow
    )
    val highPass = designHighPassFilter(
        sampleRate,
        lowPassFrequency,
        highPassNumberOfTaps,
        highPassWindow
    )

    return Filter(
        convolution(lowPass.coefficients, highPass.coefficients)
    )
}

fun designBandRejectFilter(
    lowPassFrequency: Double,
    highPassFrequency: Double,
    lowPassNumberOfTaps: Int,
    highPassNumberOfTaps: Int,
    lowPassWindow: WindowFunction,
    highPassWindow: WindowFunction,
    sampleRate: Int,
): Filter {
    val lowPass = designLowPassFilter(
        sampleRate,
        lowPassFrequency,
        lowPassNumberOfTaps,
        lowPassWindow
    )
    val highPass = designHighPassFilter(
        sampleRate,
        highPassFrequency,
        highPassNumberOfTaps,
        highPassWindow
    )

    val long = if (lowPass.coefficients.size >= highPass.coefficients.size) {
        lowPass.coefficients
    } else {
        highPass.coefficients
    }
    var short = if (lowPass.coefficients.size >= highPass.coefficients.size) {
        highPass.coefficients
    } else {
        lowPass.coefficients
    }
    val diff = (long.size - short.size) / 2
    short = MutableList(diff) { 0.0 }.also {
        it.addAll(short)
        it.addAll(List(diff) { 0.0 })
    }

    return Filter(
        long.zip(short).map { it.first + it.second }
    )
}
