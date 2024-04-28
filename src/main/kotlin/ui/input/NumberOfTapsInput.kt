package ui.input

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import ui.uiState

@Composable
fun NumberOfTapsInput() {
    var numberOfTaps by remember { uiState.numberOfTaps }

    IntInput(
        value = numberOfTaps,
        onValueChange = { numberOfTaps = it },
        label = { Text("Number of taps") }
    )
}