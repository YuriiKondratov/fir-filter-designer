package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.frequencyDomain
import core.frequencyDomainDb
import org.jetbrains.letsPlot.geom.geomLine
import org.jetbrains.letsPlot.label.xlab
import org.jetbrains.letsPlot.label.ylab
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.skia.compose.PlotPanel

@Composable
fun FiltrationVisualizationPanel(
    sampleRate: Int,
    signal: Map<Double, Double>,
    filteredSignal: Map<Double, Double>
) {
    val values = signal.values.toList()
    val filteredValues = filteredSignal.values.toList()

    val freqDomain = values.frequencyDomain(sampleRate)
    val freqDomainDb = values.frequencyDomainDb(sampleRate)
    val filteredFreqDomain = filteredValues.frequencyDomain(sampleRate)
    val filteredFreqDomainDb = filteredValues.frequencyDomainDb(sampleRate)

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        PlotPanel(
            figure = letsPlot(
                mapOf(
                    "x" to signal.keys.toList() + filteredSignal.keys.toList(),
                    "y" to values + filteredValues,
                    "" to List(values.size) { "Original" }
                            + List(filteredValues.size) { "Filtered" }
                )
            ) + geomLine {
                x = "x"
                y = "y"
                color = ""
            } + xlab("Time") + ylab("Amplitude"),
            modifier = Modifier
                .fillMaxSize()
                .weight(1F)
                .padding(10.dp)
        ) {}
        PlotPanel(
            figure = letsPlot(
                mapOf(
                    "x" to freqDomain.keys.toList() + filteredFreqDomain.keys.toList(),
                    "y" to freqDomain.values.toList() + filteredFreqDomain.values.toList(),
                    "" to List(freqDomain.values.size) { "Original" }
                            + List(filteredFreqDomain.values.size) { "Filtered" }
                )
            ) + geomLine {
                x = "x"
                y = "y"
                color = ""
            } + xlab("Frequency") + ylab("Gain"),
            modifier = Modifier
                .fillMaxSize()
                .weight(1F)
                .padding(10.dp)
        ) {}
        PlotPanel(
            figure = letsPlot(
                mapOf(
                    "x" to freqDomainDb.keys.toList() + filteredFreqDomainDb.keys.toList(),
                    "y" to freqDomainDb.values.toList() + filteredFreqDomainDb.values.toList(),
                    "" to List(freqDomainDb.values.size) { "Original" }
                            + List(filteredFreqDomainDb.values.size) { "Filtered" }
                )
            ) + geomLine {
                x = "x"
                y = "y"
                color = ""
            } + xlab("Frequency") + ylab("Gain [dB]"),
            modifier = Modifier
                .fillMaxSize()
                .weight(1F)
                .padding(10.dp)
        ) {}
    }
}