package core

import kotlin.math.PI
import kotlin.math.cos

sealed interface WindowFunction : () -> List<Double> {
    data class Rectangular(val N: Int) : WindowFunction {
        override fun invoke(): List<Double> = (0..<N).map { 1.0 }
    }

    data class Hanning(val N: Int) : WindowFunction {
        override fun invoke(): List<Double> =
            (1 - N..<N step 2).map {
                0.5 + 0.5 * cos(PI * it / (N - 1))
            }
    }

    data class Hamming(val N: Int) : WindowFunction {
        override fun invoke(): List<Double> =
            (1 - N..<N step 2).map {
                0.54 + 0.46 * cos(PI * it / (N - 1))
            }
    }

    data class Blackman(val N: Int) : WindowFunction {
        override fun invoke(): List<Double> =
            (1 - N..<N step 2).map {
                0.42 + 0.5 * cos(PI * it / (N - 1)) + 0.08 * cos(2.0 * PI * it / (N - 1))
            }
    }

    data class Bartlett(val N: Int) : WindowFunction {
        override fun invoke(): List<Double> =
            (1 - N..<N step 2).map {
                if (it <= 0) {
                    1 + it / (N - 1.0)
                } else {
                    1 - it / (N - 1.0)
                }
            }
    }
}

enum class WindowFunctionEnum(val description: String) {
    RECTANGULAR("Rectangular"),
    BARTLETT("Bartlett"),
    HANNING("Hanning"),
    HAMMING("Hamming"),
    BLACKMAN("Blackman");
}
