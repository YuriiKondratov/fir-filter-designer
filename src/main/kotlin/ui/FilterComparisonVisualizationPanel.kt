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
import org.jetbrains.letsPlot.label.xlab
import org.jetbrains.letsPlot.label.ylab
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.skia.compose.PlotPanel

@Composable
fun FilterComparisonVisualizationPanel(
    filters: Map<String, FilterInfo>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        PlotPanel(
            figure = letsPlot(
                filters.entries.associate {
                    it.key to it.value.impulseResponse
                }.toDataFrame()
            ) + geomLollipop {
                x = "x"
                y = "y"
                color = ""
            } + xlab("Номер отсчета") + ylab(""),
            modifier = Modifier
                .fillMaxSize()
                .weight(1F)
                .padding(10.dp)
        ) {}
        PlotPanel(
            figure = letsPlot(
                filters.entries.associate {
                    it.key to it.value.frequencyResponse
                }.toDataFrame()
            ) + geomLine {
                x = "x"
                y = "y"
                color = ""
            } + xlab("Частота, Гц") + ylab("Усиление, дБ"),
            modifier = Modifier
                .fillMaxSize()
                .weight(1F)
                .padding(10.dp)
        ) {}
        PlotPanel(
            figure = letsPlot(
                filters.entries.associate {
                    it.key to it.value.frequencyResponseDb
                }.toDataFrame()
            ) + geomLine {
                x = "x"
                y = "y"
                color = ""
            } + xlab("Частота, Гц") + ylab("Усиление, дБ"),
            modifier = Modifier
                .fillMaxSize()
                .weight(1F)
                .padding(10.dp)
        ) {}
    }
}

private fun Map<String, Map<Double, Double>>.toDataFrame(): Map<String, List<Any>> {
    val x = values.map { it.keys.toList() }.flatten()
    val y = values.map { it.values.toList() }.flatten()
    val colors = entries.map { entry -> List(entry.value.size) { entry.key } }.flatten()
    return mapOf(
        "x" to x,
        "y" to y,
        "" to colors
    )
}
