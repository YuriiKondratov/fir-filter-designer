package ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.FilterTypeEnum
import ui.controller.calculateHighPassFilter
import ui.controller.calculateLowPassFilter
import ui.input.IntInput
import ui.state.filterDesignWindowState

@Composable
fun FilterPropertiesInputPanel() {
    val filterType by remember { filterDesignWindowState.filterType }
    var sampleRate by remember { filterDesignWindowState.sampleRate }

    Column(
        modifier = Modifier
            .width(256.dp)
            .fillMaxHeight()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {

        IntInput(
            value = sampleRate,
            onValueChange = { sampleRate = it },
            label = { Text("Sample rate") }
        )
        WindowFunctionMenu()
        FilterTypeMenu()

        val calculationCommand: () -> Unit = when (filterType) {
            FilterTypeEnum.LOW_PASS -> {
                LowPassFilterDesignPanel()
                ::calculateLowPassFilter
            }
            FilterTypeEnum.HIGH_PASS -> {
                HighPassFilterDesignPanel()
                ::calculateHighPassFilter
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = calculationCommand
            ) {
                Text("Calculate")
            }
            RememberButtonWithDialog()
        }
    }
}
