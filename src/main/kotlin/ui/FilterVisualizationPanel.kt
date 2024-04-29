package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.FilterInfo
import org.jetbrains.letsPlot.geom.geomLine
import org.jetbrains.letsPlot.geom.geomLollipop
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.skia.compose.PlotPanel

@Composable
fun FilterVisualizationPanel(
    filter: FilterInfo
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        PlotPanel(
            figure = letsPlot(
                mapOf(
                    "Sample number" to filter.impulseResponse.keys,
                    "Amplitude" to filter.impulseResponse.values
                )
            ) + geomLollipop {
                x = "Sample number"
                y = "Amplitude"
            },
            modifier = Modifier
                .fillMaxSize()
                .weight(1F)
                .padding(10.dp)
        ) {}
        PlotPanel(
            figure = letsPlot(
                mapOf(
                    "Frequency" to filter.frequencyResponse.keys,
                    "Gain" to filter.frequencyResponse.values
                )
            ) + geomLine {
                x = "Frequency"
                y = "Gain"
            },
            modifier = Modifier
                .fillMaxSize()
                .weight(1F)
                .padding(10.dp)
        ) {}
        PlotPanel(
            figure = letsPlot(
                mapOf(
                    "Frequency" to filter.frequencyResponseDb.keys,
                    "Gain [dB]" to filter.frequencyResponseDb.values
                )
            ) + geomLine {
                x = "Frequency"
                y = "Gain [dB]"
            },
            modifier = Modifier
                .fillMaxSize()
                .weight(1F)
                .padding(10.dp)
        ) {}
    }
}
