package ui.controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import core.FREQUENCY_RESPONSE_POINTS
import core.coefficients
import core.convolution
import core.frequencyResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ui.state.filteringWindowState
import ui.state.getFilter
import ui.state.sharedState

fun visualizeSignalFrequencyResponse(time: Float) {
    val file = filteringWindowState.file.value ?: return
    val from = (time * file.frameRate).toLong()

    var signal by filteringWindowState.signal
    signal = file.getData(from, from + FREQUENCY_RESPONSE_POINTS)

    val fr = signal.map { it.toDouble() }.frequencyResponse(file.sampleRate)
    var signalFr by filteringWindowState.signalFrequencyResponse
    signalFr = fr.entries.associate { it.key to it.value / fr.size }
}


fun applyFilterToSignal() {
    GlobalScope.launch {
        val file = filteringWindowState.file.value ?: return@launch
        val signal = filteringWindowState.signal.value
        val chosenFilter = filteringWindowState.chosenFilter.value ?: return@launch
        var filteredSignal = convolution(
            signal.map { it.toDouble() },
            sharedState.getFilter(chosenFilter)?.coefficients() ?: return@launch
        )
        val diff = (filteredSignal.size - signal.size) / 2
        filteredSignal = filteredSignal.subList(diff, filteredSignal.size - diff)
        val fr = filteredSignal.frequencyResponse(file.sampleRate)
        var filteredSignalFr by filteringWindowState.filteredSignalFrequencyResponse
        filteredSignalFr = fr.entries.associate { it.key to it.value / fr.size }
    }
}
