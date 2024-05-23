package controls

import core.WindowFunction
import core.WindowFunctionType
import core.calculateInfo
import core.designBandPassFilter
import core.designBandRejectFilter
import core.designHighPassFilter
import core.designLowPassFilter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import state.bandPassFilterDesignState
import state.bandRejectFilterDesignState
import state.filterDesignWindowState
import state.highPassFilterDesignState
import state.lowPassFilterDesignState
import state.setCurrentFilter

fun calculateLowPassFilter() {
    GlobalScope.launch {
        val numberOfTaps = lowPassFilterDesignState.numberOfTaps.value!!
        val sampleRate = filterDesignWindowState.sampleRate.value!!
        val filter = designLowPassFilter(
            sampleRate,
            lowPassFilterDesignState.lowPassFrequency.value!!,
            numberOfTaps,
            calculateWindow(filterDesignWindowState.windowFunction.value, numberOfTaps)
        )
        filterDesignWindowState.setCurrentFilter(
            filter.calculateInfo(sampleRate)
        )
    }
}

fun validateLowPassFilterData(): String? {
    val numberOfTaps = lowPassFilterDesignState.numberOfTaps.value
    val sampleRate = filterDesignWindowState.sampleRate.value
    val lowPassFrequency = lowPassFilterDesignState.lowPassFrequency.value
    if (numberOfTaps == null || numberOfTaps < 1 || numberOfTaps > 1023 || numberOfTaps % 2 == 0) {
        return "Неверно задано значение поля \"Количество отсчетов\": $numberOfTaps.\n" +
                "Значение должно быть целым нечетным числом в пределах от 1 до 1023"
    }
    if (sampleRate == null || sampleRate < 0) {
        return "Неверно задано значение поля \"Частота дискретизации\": $sampleRate.\n" +
                "Значение должно быть числом больше нуля."
    }
    if (lowPassFrequency == null || lowPassFrequency < 0 || lowPassFrequency >= sampleRate / 2) {
        return "Неверно задано значение поля \"Частота среза\": $lowPassFrequency.\n" +
                "Значение должно быть числом в пределах от 0 до ${sampleRate / 2}"
    }
    return null
}

fun calculateHighPassFilter() {
    GlobalScope.launch {
        val numberOfTaps = highPassFilterDesignState.numberOfTaps.value!!
        val sampleRate = filterDesignWindowState.sampleRate.value!!
        val filter = designHighPassFilter(
            sampleRate,
            highPassFilterDesignState.highPassFrequency.value!!,
            numberOfTaps,
            calculateWindow(filterDesignWindowState.windowFunction.value, numberOfTaps)
        )
        filterDesignWindowState.setCurrentFilter(
            filter.calculateInfo(sampleRate)
        )
    }
}

fun validateHighPassFilterData(): String? {
    val numberOfTaps = highPassFilterDesignState.numberOfTaps.value
    val sampleRate = filterDesignWindowState.sampleRate.value
    val highPassFrequency = highPassFilterDesignState.highPassFrequency.value
    if (numberOfTaps == null || numberOfTaps < 1 || numberOfTaps > 1023 || numberOfTaps % 2 == 0) {
        return "Неверно задано значение поля \"Количество отсчетов\": $numberOfTaps.\n" +
                "Значение должно быть целым нечетным числом в пределах от 1 до 1023"
    }
    if (sampleRate == null || sampleRate < 0) {
        return "Неверно задано значение поля \"Частота дискретизации\": $sampleRate.\n" +
                "Значение должно быть числом больше нуля."
    }
    if (highPassFrequency == null || highPassFrequency < 0 || highPassFrequency >= sampleRate / 2) {
        return "Неверно задано значение поля \"Частота среза\": $highPassFrequency.\n" +
                "Значение должно быть числом в пределах от 0 до ${sampleRate / 2}"
    }
    return null
}

fun calculateBandRejectFilter() {
    GlobalScope.launch {
        val numberOfLowPassTaps = bandRejectFilterDesignState.numberOfLowPassTaps.value!!
        val numberOfHighPassTaps = bandRejectFilterDesignState.numberOfHighPassTaps.value!!
        val sampleRate = filterDesignWindowState.sampleRate.value!!
        val filter = designBandRejectFilter(
            bandRejectFilterDesignState.lowPassFrequency.value!!,
            bandRejectFilterDesignState.highPassFrequency.value!!,
            numberOfLowPassTaps,
            numberOfHighPassTaps,
            calculateWindow(filterDesignWindowState.windowFunction.value, numberOfLowPassTaps),
            calculateWindow(filterDesignWindowState.windowFunction.value, numberOfHighPassTaps),
            sampleRate
        )
        filterDesignWindowState.setCurrentFilter(
            filter.calculateInfo(sampleRate)
        )
    }
}

fun validateBandRejectFilterData(): String? {
    val numberOfLowPassTaps = bandRejectFilterDesignState.numberOfLowPassTaps.value
    val numberOfHighPassTaps = bandRejectFilterDesignState.numberOfHighPassTaps.value
    val sampleRate = filterDesignWindowState.sampleRate.value
    val lowPassFrequency = bandRejectFilterDesignState.lowPassFrequency.value
    val highPassFrequency = bandRejectFilterDesignState.highPassFrequency.value
    if (
        numberOfLowPassTaps == null || numberOfLowPassTaps < 1 ||
        numberOfLowPassTaps > 1023 || numberOfLowPassTaps % 2 == 0
    ) {
        return "Неверно задано значение поля \"Количество отсчетов ФНЧ\": $numberOfLowPassTaps.\n" +
                "Значение должно быть целым нечетным числом в пределах от 1 до 1023"
    }
    if (
        numberOfHighPassTaps == null || numberOfHighPassTaps < 1 ||
        numberOfHighPassTaps > 1023 || numberOfHighPassTaps % 2 == 0
    ) {
        return "Неверно задано значение поля \"Количество отсчетов ФВЧ\": $numberOfHighPassTaps.\n" +
                "Значение должно быть целым нечетным числом в пределах от 1 до 1023"
    }
    if (sampleRate == null || sampleRate < 0) {
        return "Неверно задано значение поля \"Частота дискретизации\": $sampleRate.\n" +
                "Значение должно быть числом больше нуля."
    }
    if (lowPassFrequency == null || lowPassFrequency < 0 || lowPassFrequency >= sampleRate / 2) {
        return "Неверно задано значение поля \"Частота среза ФНЧ\": $lowPassFrequency.\n" +
                "Значение должно быть числом в пределах от 0 до ${sampleRate / 2}"
    }
    if (highPassFrequency == null || highPassFrequency < 0 || highPassFrequency >= sampleRate / 2) {
        return "Неверно задано значение поля \"Частота среза ФВЧ\": $highPassFrequency.\n" +
                "Значение должно быть числом в пределах от 0 до ${sampleRate / 2}"
    }
    if (highPassFrequency <= lowPassFrequency) {
        return "Частота среза ФВЧ должна превышать частоту среза ФНЧ"
    }
    return null
}

fun calculateBandPassFilter() {
    GlobalScope.launch {
        val numberOfLowPassTaps = bandPassFilterDesignState.numberOfLowPassTaps.value!!
        val numberOfHighPassTaps = bandPassFilterDesignState.numberOfHighPassTaps.value!!
        val sampleRate = filterDesignWindowState.sampleRate.value!!
        val filter = designBandPassFilter(
            bandPassFilterDesignState.lowPassFrequency.value!!,
            bandPassFilterDesignState.highPassFrequency.value!!,
            numberOfLowPassTaps,
            numberOfHighPassTaps,
            calculateWindow(filterDesignWindowState.windowFunction.value, numberOfLowPassTaps),
            calculateWindow(filterDesignWindowState.windowFunction.value, numberOfHighPassTaps),
            sampleRate
        )
        filterDesignWindowState.setCurrentFilter(
            filter.calculateInfo(sampleRate)
        )
    }
}

fun validateBandPassFilterData(): String? {
    val numberOfLowPassTaps = bandPassFilterDesignState.numberOfLowPassTaps.value
    val numberOfHighPassTaps = bandPassFilterDesignState.numberOfHighPassTaps.value
    val sampleRate = filterDesignWindowState.sampleRate.value
    val lowPassFrequency = bandPassFilterDesignState.lowPassFrequency.value
    val highPassFrequency = bandPassFilterDesignState.highPassFrequency.value
    if (
        numberOfLowPassTaps == null || numberOfLowPassTaps < 1 ||
        numberOfLowPassTaps > 1023 || numberOfLowPassTaps % 2 == 0
    ) {
        return "Неверно задано значение поля \"Количество отсчетов ФНЧ\": $numberOfLowPassTaps.\n" +
                "Значение должно быть целым нечетным числом в пределах от 1 до 1023"
    }
    if (
        numberOfHighPassTaps == null || numberOfHighPassTaps < 1 ||
        numberOfHighPassTaps > 1023 || numberOfHighPassTaps % 2 == 0
    ) {
        return "Неверно задано значение поля \"Количество отсчетов ФВЧ\": $numberOfHighPassTaps.\n" +
                "Значение должно быть целым нечетным числом в пределах от 1 до 1023"
    }
    if (sampleRate == null || sampleRate < 0) {
        return "Неверно задано значение поля \"Частота дискретизации\": $sampleRate.\n" +
                "Значение должно быть числом больше нуля."
    }
    if (lowPassFrequency == null || lowPassFrequency < 0 || lowPassFrequency >= sampleRate / 2) {
        return "Неверно задано значение поля \"Частота среза ФНЧ\": $lowPassFrequency.\n" +
                "Значение должно быть числом в пределах от 0 до ${sampleRate / 2}"
    }
    if (highPassFrequency == null || highPassFrequency < 0 || highPassFrequency >= sampleRate / 2) {
        return "Неверно задано значение поля \"Частота среза ФВЧ\": $highPassFrequency.\n" +
                "Значение должно быть числом в пределах от 0 до ${sampleRate / 2}"
    }
    if (highPassFrequency <= lowPassFrequency) {
        return "Частота среза ФВЧ должна превышать частоту среза ФНЧ"
    }
    return null
}

private fun calculateWindow(type: WindowFunctionType, size: Int): WindowFunction =
    when (type) {
        WindowFunctionType.RECTANGULAR -> WindowFunction.Rectangular(size)
        WindowFunctionType.BARTLETT -> WindowFunction.Bartlett(size)
        WindowFunctionType.HANNING -> WindowFunction.Hanning(size)
        WindowFunctionType.HAMMING -> WindowFunction.Hamming(size)
        WindowFunctionType.BLACKMAN -> WindowFunction.Blackman(size)
    }
