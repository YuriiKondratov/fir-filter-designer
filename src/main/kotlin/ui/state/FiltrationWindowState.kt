package ui.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.io.File

val filtrationWindowState = FiltrationWindowState()

class FiltrationWindowState {
    val startTime = mutableStateOf<Double?>(null)
    val endTime = mutableStateOf<Double?>(null)
    val file = mutableStateOf<File?>(null)
    val chosenFilters = mutableStateOf<Set<String>>(setOf())
    val visualizedSignalPart = mutableStateOf<Map<Double, Double>>(mapOf())
    val filteredVisualizedSignalPart = mutableStateOf<Map<Double, Double>>(mapOf())
}

fun FiltrationWindowState.chooseFilter(filterName: String) {
    var chosenFilters by chosenFilters
    chosenFilters = chosenFilters.toMutableSet().let {
        if (filterName in it) {
            it.remove(filterName)
        } else {
            it.add(filterName)
        }
        it
    }
}

fun FiltrationWindowState.chooseVisualizedPart(signal: Map<Double, Double>) {
    var visualizedSignalPart by visualizedSignalPart
    visualizedSignalPart = signal
}
