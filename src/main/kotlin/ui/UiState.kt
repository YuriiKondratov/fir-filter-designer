package ui

import androidx.compose.runtime.mutableStateOf
import core.FilterTypeEnum
import core.WindowFunctionEnum

val uiState = UiState()

class UiState {
    val sampleRate = mutableStateOf<Int?>(null)
    val numberOfTaps = mutableStateOf<Int?>(null)
    val windowFunction = mutableStateOf(WindowFunctionEnum.RECTANGULAR)
    val filterType = mutableStateOf(FilterTypeEnum.LOW_PASS)

    val lowCutFrequency = mutableStateOf<Double?>(null)
    val highCutFrequency = mutableStateOf<Double?>(null)

    val impulseResponse = mutableStateOf<Map<Double, Double>>(mapOf())
    val frequencyResponse = mutableStateOf<Map<Double, Double>>(mapOf())
    val frequencyResponseDb = mutableStateOf<Map<Double, Double>>(mapOf())
}
