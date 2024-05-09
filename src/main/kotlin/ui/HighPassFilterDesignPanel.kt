package ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import ui.input.DoubleInput
import ui.input.IntInput
import ui.state.highPassFilterDesignState

@Composable
fun HighPassFilterDesignPanel() {
    var highPassFrequency by remember { highPassFilterDesignState.highPassFrequency }
    var numberOfTaps by remember { highPassFilterDesignState.numberOfTaps }

    IntInput(
        value = numberOfTaps,
        onValueChange = { numberOfTaps = it },
        label = { Text("Количество отсчетов") }
    )
    DoubleInput(
        value = highPassFrequency,
        onValueChange = { highPassFrequency = it },
        label = { Text("Частота среза, Гц") }
    )
}
