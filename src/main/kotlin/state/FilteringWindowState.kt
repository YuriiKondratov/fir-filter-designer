package state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import core.WavFile

val filteringWindowState = FilteringWindowState()

class FilteringWindowState {
    val file = mutableStateOf<WavFile?>(null)
    val chosenFilter = mutableStateOf<String?>(null)
    val signal = mutableStateOf<List<Short>>(listOf())
    val signalFrequencyResponse = mutableStateOf<Map<Double, Double>>(mapOf())
    val filteredSignalFrequencyResponse = mutableStateOf<Map<Double, Double>>(mapOf())
}

fun FilteringWindowState.chooseFilter(filterName: String) {
    var chosenFilter by chosenFilter
    chosenFilter = filterName
}
