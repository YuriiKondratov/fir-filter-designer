package core

enum class FilterTypeEnum(val description: String) {
    LOW_PASS("Фильтр Низких Частот (ФНЧ)"),
    HIGH_PASS("Фильтр Высоких Частот (ФВЧ)"),
    BAND_PASS("Полосовой фильтр"),
    BAND_REJECT("Режекторный фильтр"),
}
