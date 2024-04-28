package ui.input

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import ui.uiState

@Composable
fun HighCutFrequencyInput() {
    var highCutFrequency by remember { uiState.highCutFrequency }

    DoubleInput(
        value = highCutFrequency,
        onValueChange = { highCutFrequency = it },
        label = { Text("High cut frequency") }
    )
}
