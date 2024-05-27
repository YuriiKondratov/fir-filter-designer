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
import state.bandRejectFilterDesignState

@Composable
fun BandRejectFilterDesignPanel() {
    var numberOfLowPassTaps by remember { bandRejectFilterDesignState.numberOfLowPassTaps }
    var numberOfHighPassTaps by remember { bandRejectFilterDesignState.numberOfHighPassTaps }
    var lowPassFrequency by remember { bandRejectFilterDesignState.lowPassFrequency }
    var highPassFrequency by remember { bandRejectFilterDesignState.highPassFrequency }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IntInput(
            value = numberOfLowPassTaps,
            onValueChange = { numberOfLowPassTaps = it },
            modifier = Modifier.weight(1F),
            label = { Text("Количество отсчетов ФНЧ") }
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
        IntInput(
            value = numberOfHighPassTaps,
            onValueChange = { numberOfHighPassTaps = it },
            modifier = Modifier.weight(1F),
            label = { Text("Количество отсчетов ФВЧ") }
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
            value = lowPassFrequency,
            onValueChange = { lowPassFrequency = it },
            modifier = Modifier.weight(1F),
            label = { Text("Частота среза ФНЧ, Гц") }
        )
        InfoIconWithTooltip(
            "Положительное число, не превосходящее\nполовину частоты дискретизации",
            modifier = Modifier.weight(1F),
            alignment = Alignment.TopStart,
        )
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        DoubleInput(
            value = highPassFrequency,
            onValueChange = { highPassFrequency = it },
            modifier = Modifier.weight(1F),
            label = { Text("Частота среза ФВЧ, Гц") }
        )
        InfoIconWithTooltip(
            "Положительное число, не превосходящее\nполовину частоты дискретизации",
            modifier = Modifier.weight(1F),
            alignment = Alignment.TopStart,
        )
    }
}
