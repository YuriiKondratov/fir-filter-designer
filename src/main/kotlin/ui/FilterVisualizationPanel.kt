import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.uiState
import org.jetbrains.letsPlot.geom.geomLine
import org.jetbrains.letsPlot.geom.geomLollipop
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.skia.compose.PlotPanel

@Composable
fun FilterVisualizationPanel() {
    val impulseResponse by remember { uiState.impulseResponse }
    val frequencyResponse by remember { uiState.frequencyResponse }
    val frequencyResponseDb by remember { uiState.frequencyResponseDb }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        PlotPanel(
            figure = letsPlot(
                mapOf(
                    "Sample number" to impulseResponse.keys,
                    "Amplitude" to impulseResponse.values
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
                    "Frequency" to frequencyResponse.keys,
                    "Gain" to frequencyResponse.values
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
                    "Frequency" to frequencyResponseDb.keys,
                    "Gain [dB]" to frequencyResponseDb.values
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
