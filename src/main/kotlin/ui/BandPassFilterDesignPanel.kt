package ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import ui.input.DoubleInput
import ui.input.IntInput
import state.bandPassFilterDesignState

@Composable
fun BandPassFilterDesignPanel() {
    var numberOfLowPassTaps by remember { bandPassFilterDesignState.numberOfLowPassTaps }
    var numberOfHighPassTaps by remember { bandPassFilterDesignState.numberOfHighPassTaps }
    var lowPassFrequency by remember { bandPassFilterDesignState.lowPassFrequency }
    var highPassFrequency by remember { bandPassFilterDesignState.highPassFrequency }

    IntInput(
        value = numberOfLowPassTaps,
        onValueChange = { numberOfLowPassTaps = it },
        label = { Text("Количество отсчетов ФНЧ") }
    )
    IntInput(
        value = numberOfHighPassTaps,
        onValueChange = { numberOfHighPassTaps = it },
        label = { Text("Количество отсчетов ФВЧ") }
    )
    DoubleInput(
        value = lowPassFrequency,
        onValueChange = { lowPassFrequency = it },
        label = { Text("Частота среза ФНЧ, Гц") }
    )
    DoubleInput(
        value = highPassFrequency,
        onValueChange = { highPassFrequency = it },
        label = { Text("Частота среза ФВЧ, Гц") }
    )
}
