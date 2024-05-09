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
                    "x" to filter.impulseResponse.keys,
                    "y" to filter.impulseResponse.values
                )
            ) + geomLollipop {
                x = "x"
                y = "y"
            } + xlab("Номер отсчета") + ylab(""),
            modifier = Modifier
                .fillMaxSize()
                .weight(1F)
                .padding(10.dp)
        ) {}
        PlotPanel(
            figure = letsPlot(
                mapOf(
                    "x" to filter.frequencyResponse.keys,
                    "y" to filter.frequencyResponse.values
                )
            ) + geomLine {
                x = "x"
                y = "y"
            } + xlab("Частота, Гц") + ylab("Усиление"),
            modifier = Modifier
                .fillMaxSize()
                .weight(1F)
                .padding(10.dp)
        ) {}
        PlotPanel(
            figure = letsPlot(
                mapOf(
                    "x" to filter.frequencyResponseDb.keys,
                    "y" to filter.frequencyResponseDb.values
                )
            ) + geomLine {
                x = "x"
                y = "y"
            } + xlab("Частота, Гц") + ylab("Усиление, дБ"),
            modifier = Modifier
                .fillMaxSize()
                .weight(1F)
                .padding(10.dp)
        ) {}
    }
}
