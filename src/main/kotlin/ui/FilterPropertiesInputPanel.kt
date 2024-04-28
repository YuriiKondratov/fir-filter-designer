package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import core.FilterTypeEnum
import ui.input.HighCutFrequencyInput
import ui.input.LowCutFrequencyInput
import ui.input.NumberOfTapsInput
import ui.input.SampleRateInput

@Composable
fun FilterPropertiesInputPanel() {
    val filterType by remember { uiState.filterType }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(10.dp)
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {

        SampleRateInput()
        NumberOfTapsInput()
        WindowFunctionMenu()
        FilterTypeMenu()

        when (filterType) {
            FilterTypeEnum.LOW_PASS -> LowCutFrequencyInput()
            FilterTypeEnum.HIGH_PASS -> HighCutFrequencyInput()
//            FilterTypeEnum.BAND_PASS, FilterTypeEnum.BAND_REJECT -> {
//                LowCutFrequencyInput()
//                HighCutFrequencyInput()
//            }
        }

        CalculationButton()
    }
}