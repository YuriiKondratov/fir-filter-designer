package ui.input

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import ui.uiState

@Composable
fun SampleRateInput() {
    var sampleRate by remember { uiState.sampleRate }

    IntInput(
        value = sampleRate,
        onValueChange = { sampleRate = it },
        label = { Text("Sample rate") }
    )
}
