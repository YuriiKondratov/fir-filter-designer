package core

import org.apache.commons.math3.analysis.function.Sinc
import org.apache.commons.math3.complex.Complex
import org.apache.commons.math3.util.FastMath.E
import org.apache.commons.math3.util.FastMath.PI
import org.apache.commons.math3.util.MathArrays

fun sinc(n: Double) = Sinc(true).value(n)

fun dft(input: List<Double>): List<Complex> {
    val N = input.size
    val factor = { m: Int, n: Int ->
        Complex(E).pow(
            Complex.I.multiply(-2 * PI * n * m / N)
        )
    }
    return (0 until N).map { m ->
        input.withIndex().map { (n, x) ->
            factor(m, n).multiply(x)
        }.reduce { first, second ->
            first.add(second)
        }
    }
}

fun convolution(x: List<Double>, y: List<Double>) =
    MathArrays.convolve(
        x.toDoubleArray(),
        y.toDoubleArray()
    ).toList()
