package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.letsPlot.geom.geomLine
import org.jetbrains.letsPlot.label.xlab
import org.jetbrains.letsPlot.label.ylab
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.scale.scaleXLog2
import org.jetbrains.letsPlot.skia.compose.PlotPanel
import controls.applyFilterToSignal
import controls.visualizeSignalFrequencyResponse
import ui.input.WavFileInput
import state.filteringWindowState
import kotlin.math.round

@Composable
fun FilteringPanel() {
    var file by remember { filteringWindowState.file }
    val chosenFilter by remember { filteringWindowState.chosenFilter }
    var sliderPosition by remember { mutableFloatStateOf(0f) }

    var errorMessage by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    ErrorMessageDialog(
        isError,
        errorMessage
    ) {
        isError = false
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.White)
    ) {
        Row(
            modifier = Modifier.weight(1F)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.width(350.dp).padding(10.dp).fillMaxHeight()
            ) {
                Column {
                    WavFileInput(
                        onChoice = {
                            it?.duration?.let { dur ->
                                if (dur < 1) {
                                    errorMessage = "Длительность аудио должна превышать 1 секунду"
                                    isError = true
                                    file = null
                                } else {
                                    file = it
                                    visualizeSignalFrequencyResponse(sliderPosition)
                                }
                            } ?: { file = null }
                            Unit
                        },
                        onError = {
                            errorMessage = "Ошибка при считывании файла: ${it.message}"
                            isError = true
                        }
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Название файла: ${file?.name ?: ""}"
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Длительность, с: ${file?.duration ?: ""}"
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Частота дискретизации, Гц: ${file?.sampleRate ?: ""}"
                    )
                }

                Column {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Slider(
                            enabled = file != null,
                            value = sliderPosition,
                            onValueChange = { sliderPosition = round(it * 100) / 100 },
                            valueRange = file?.let {
                                (0.0F..it.duration)
                            } ?: (0.0F..1.0F),
                        )
                        Text(
                            text = "$sliderPosition с"
                        )
                    }
                    Button(
                        enabled = file != null,
                        modifier = Modifier,
                        onClick = {
                            GlobalScope.launch {
                                visualizeSignalFrequencyResponse(sliderPosition)
                                applyFilterToSignal()
                            }
                        }
                    ) {
                        Text("Показать АЧХ")
                    }
                }

                Column {
                    ChooseFilterButtonWithDialog(
                        enabled = file != null,
                        onSelect = {
                            GlobalScope.launch {
                                visualizeSignalFrequencyResponse(sliderPosition)
                                applyFilterToSignal()
                            }
                        }
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Фильтр: ${chosenFilter ?: ""}"
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                val signalFr by remember { filteringWindowState.signalFrequencyResponse }
                val filteredSignalFr by remember { filteringWindowState.filteredSignalFrequencyResponse }

                PlotPanel(
                    figure = letsPlot(
                        mapOf(
                            "x" to signalFr.keys.toList() + filteredSignalFr.keys.toList(),
                            "y" to signalFr.values.toList() + filteredSignalFr.values.toList(),
                            "" to List(signalFr.size) { "До фильтрации" }
                                    + List(filteredSignalFr.size) { "После фильтрации" }
                        )
                    ) + geomLine {
                        x = "x"
                        y = "y"
                        color = ""
                    } + xlab("Частота, Гц") + ylab("Амплитуда") + scaleXLog2(),
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1F)
                        .padding(10.dp)
                ) {}
            }
        }
    }
}
