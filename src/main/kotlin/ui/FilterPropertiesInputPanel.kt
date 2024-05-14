package ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.FilterTypeEnum
import ui.controller.calculateBandPassFilter
import ui.controller.calculateBandRejectFilter
import ui.controller.calculateHighPassFilter
import ui.controller.calculateLowPassFilter
import ui.controller.validateBandPassFilterData
import ui.controller.validateBandRejectFilterData
import ui.controller.validateHighPassFilterData
import ui.controller.validateLowPassFilterData
import ui.input.IntInput
import ui.state.filterDesignWindowState

@Composable
fun FilterPropertiesInputPanel() {
    val filterType by remember { filterDesignWindowState.filterType }
    var sampleRate by remember { filterDesignWindowState.sampleRate }

    var errorMessage by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    ErrorMessageDialog(
        isError,
        errorMessage
    ) {
        isError = false
    }

    Column(
        modifier = Modifier
            .width(300.dp)
            .fillMaxHeight()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {

        IntInput(
            value = sampleRate,
            onValueChange = { sampleRate = it },
            label = { Text("Частота дискретизации, Гц") }
        )
        WindowFunctionMenu()
        FilterTypeMenu()

        val calculationCommand: () -> Unit = when (filterType) {
            FilterTypeEnum.LOW_PASS -> {
                LowPassFilterDesignPanel();
                {
                    val error = validateLowPassFilterData()
                    error?.run {
                        errorMessage = this
                        isError = true
                    } ?: calculateLowPassFilter()
                }
            }
            FilterTypeEnum.HIGH_PASS -> {
                HighPassFilterDesignPanel();
                {
                    val error = validateHighPassFilterData()
                    error?.run {
                        errorMessage = this
                        isError = true
                    } ?: calculateBandPassFilter()
                }
            }
            FilterTypeEnum.BAND_PASS -> {
                BandPassFilterDesignPanel();
                {
                    val error = validateBandPassFilterData()
                    error?.run {
                        errorMessage = this
                        isError = true
                    } ?: calculateHighPassFilter()
                }
            }
            FilterTypeEnum.BAND_REJECT -> {
                BandRejectFilterDesignPanel();
                {
                    val error = validateBandRejectFilterData()
                    error?.run {
                        errorMessage = this
                        isError = true
                    } ?: calculateBandRejectFilter()
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = calculationCommand
            ) {
                Text("Рассчитать")
            }
            RememberButtonWithDialog()
            Button(
                onClick = calculationCommand
            ) {
                Text("Экспортировать")
            }
        }
    }
}
