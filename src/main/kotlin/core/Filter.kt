package core

data class Filter(
    val coefficients: List<Double>
)

data class FilterInfo(
    val impulseResponse: Map<Double, Double>,
    val frequencyResponse: Map<Double, Double>,
    val frequencyResponseDb: Map<Double, Double>
)

fun Filter.calculateInfo(sampleRate: Int) =
    FilterInfo(
        impulseResponse(),
        coefficients.frequencyDomain(sampleRate),
        coefficients.frequencyDomainDb(sampleRate)
    )
