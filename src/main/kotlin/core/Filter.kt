package core

data class Filter(
    val coefficients: List<Double>
)

data class FilterInfo(
    val impulseResponse: Map<Double, Double>,
    val frequencyResponse: Map<Double, Double>,
    val frequencyResponseDb: Map<Double, Double>
)

fun FilterInfo.coefficients(): List<Double> =
    impulseResponse.values.toList()

fun Filter.calculateInfo(sampleRate: Int) =
    FilterInfo(
        impulseResponse(),
        coefficients.frequencyResponse(sampleRate),
        coefficients.frequencyResponseDb(sampleRate)
    )

enum class FilterTypeEnum(val description: String) {
    LOW_PASS("Фильтр Низких Частот (ФНЧ)"),
    HIGH_PASS("Фильтр Высоких Частот (ФВЧ)"),
    BAND_PASS("Полосовой фильтр"),
    BAND_REJECT("Режекторный фильтр"),
}
