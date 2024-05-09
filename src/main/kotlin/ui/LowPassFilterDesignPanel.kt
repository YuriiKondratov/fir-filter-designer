package ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import ui.input.DoubleInput
import ui.input.IntInput
import ui.state.lowPassFilterDesignState

@Composable
fun LowPassFilterDesignPanel() {
    var lowCutFrequency by remember { lowPassFilterDesignState.lowPassFrequency }
    var numberOfTaps by remember { lowPassFilterDesignState.numberOfTaps }

    IntInput(
        value = numberOfTaps,
        onValueChange = { numberOfTaps = it },
        label = { Text("Количество отсчетов") }
    )
    DoubleInput(
        value = lowCutFrequency,
        onValueChange = { lowCutFrequency = it },
        label = { Text("Частота среза, Гц") }
    )
}
