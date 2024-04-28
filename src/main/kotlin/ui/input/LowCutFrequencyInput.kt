package ui.input

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import ui.uiState

@Composable
fun LowCutFrequencyInput() {
    var lowCutFrequency by remember { uiState.lowCutFrequency }

    DoubleInput(
        value = lowCutFrequency,
        onValueChange = { lowCutFrequency = it },
        label = { Text("Low cut frequency") }
    )
}
