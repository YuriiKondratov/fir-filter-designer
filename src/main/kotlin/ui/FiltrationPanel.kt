package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import core.applyFilter
import core.frequencyDomain
import org.jetbrains.letsPlot.geom.geomLine
import org.jetbrains.letsPlot.label.xlab
import org.jetbrains.letsPlot.label.ylab
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.skia.compose.PlotPanel
import ui.controller.visualizeInputSignal
import ui.input.DoubleInput
import ui.input.WavFileInput
import ui.state.filterDesignWindowState
import ui.state.filtrationWindowState
import java.io.File
import kotlin.math.PI
import kotlin.math.cos

@Composable
fun FiltrationPanel() {
    var file by remember { filtrationWindowState.file }
    var startTime by remember { filtrationWindowState.startTime }
    var endTime by remember { filtrationWindowState.endTime }

    Row(
        modifier = Modifier
            .background(color = Color.White)
    ) {
//        FilterList(
//            rememberedFilters.keys
//        )
//        if (currentFilter.impulseResponse.isEmpty()) { return }
//        val sampleRate = 100
//        val signal = getSignal()
//        val filtered = signal.applyFilter(
//            currentFilter.impulseResponse.values.toList()
//        )
//
//        FiltrationVisualizationPanel(
//            sampleRate = sampleRate,
//            signal,
//            filtered
//        )
        Column(
            modifier = Modifier.width(256.dp)
        ) {
            FileChooser(
                onChoice = {
                    file = it
                }
            )
//            FilterList()
            DoubleInput(
                value = startTime,
                onValueChange = { startTime = it },
                label = { Text("Start time") }
            )
            DoubleInput(
                value = endTime,
                onValueChange = { endTime = it },
                label = { Text("End time") }
            )
            Button(
                onClick = {
                    visualizeInputSignal()
                }
            ) {
                Text("Show")
            }
            Button(
                onClick = {
                    var filteredVisualizedSignalPart by filtrationWindowState.filteredVisualizedSignalPart
                    filteredVisualizedSignalPart = filtrationWindowState.visualizedSignalPart.value.applyFilter(
                        filterDesignWindowState.currentFilter.value.impulseResponse.values.toList()
                    )
                }
            ) {
                Text("Apply current filter")
            }
        }
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            val signal by remember { filtrationWindowState.visualizedSignalPart }
            val filteredSignal by remember { filtrationWindowState.filteredVisualizedSignalPart }

            PlotPanel(
                figure = letsPlot(
                    mapOf(
                        "x" to signal.keys.toList(),
                        "y" to signal.values.toList()
                    )
                ) + geomLine {
                    x = "x"
                    y = "y"
                } + xlab("Time") + ylab("Amplitude"),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1F)
                    .padding(10.dp)
            ) {}
            PlotPanel(
                figure = letsPlot(
                    mapOf(
                        "x" to filteredSignal.keys.toList(),
                        "y" to filteredSignal.values.toList()
                    )
                ) + geomLine {
                    x = "x"
                    y = "y"
                } + xlab("Time") + ylab("Amplitude"),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1F)
                    .padding(10.dp)
            ) {}

            var frequencyDomain = signal.values.toList().frequencyDomain(8000)
            frequencyDomain = frequencyDomain.entries.associate {
                it.key to it.value / frequencyDomain.size
            }

            PlotPanel(
                figure = letsPlot(
                    mapOf(
                        "x" to frequencyDomain.keys.toList(),
                        "y" to frequencyDomain.values.toList()
                    )
                ) + geomLine {
                    x = "x"
                    y = "y"
                } + xlab("Frequency") + ylab("Gain"),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1F)
                    .padding(10.dp)
            ) {}
        }
    }
}

@Composable
fun FileChooser(
    onChoice: (File) -> Unit
) {
    Row {
        var fileName by remember { mutableStateOf("") }

        WavFileInput(
            onChoice = {
                fileName = it.name
                onChoice(it)
            }
        )
        Text(fileName)
    }
}

fun f(x: Double): Double {
    val m = 2 * PI
    return cos(100 * m * x) + cos(200 * m * x) + cos(300 * m * x) + cos(400 * m * x)
}


