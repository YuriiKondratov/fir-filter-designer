package ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ui.input.DoubleInput
import ui.input.IntInput
import state.lowPassFilterDesignState

@Composable
fun LowPassFilterDesignPanel() {
    var lowCutFrequency by remember { lowPassFilterDesignState.lowPassFrequency }
    var numberOfTaps by remember { lowPassFilterDesignState.numberOfTaps }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IntInput(
            value = numberOfTaps,
            onValueChange = { numberOfTaps = it },
            modifier = Modifier.weight(1F),
            label = { Text("Количество отсчетов") }
        )
        InfoIconWithTooltip(
            "Нечетное число в интервале [1, 1023]",
            modifier = Modifier.weight(1F),
            alignment = Alignment.TopStart,
        )
    }


    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        DoubleInput(
            value = lowCutFrequency,
            onValueChange = { lowCutFrequency = it },
            modifier = Modifier.weight(1F),
            label = { Text("Частота среза, Гц") }
        )
        InfoIconWithTooltip(
            "Положительное число, не превосходящее\nполовину частоты дискретизации",
            modifier = Modifier.weight(1F),
            alignment = Alignment.TopStart,
        )
    }
}
