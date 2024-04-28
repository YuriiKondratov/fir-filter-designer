package ui

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import core.FilterTypeEnum
import core.WindowFunction
import core.WindowFunctionEnum
import core.designHighPassFilter
import core.designLowPassFilter
import core.frequencyResponse
import core.frequencyResponseDb
import core.impulseResponse

@Composable
fun CalculationButton() {
    val filterType by remember { uiState.filterType }
    val windowType by remember { uiState.windowFunction }

    Button(
        onClick = {
            val windowFunction = when (windowType) {
                WindowFunctionEnum.RECTANGULAR -> WindowFunction.Rectangular(uiState.numberOfTaps.value!!)
                WindowFunctionEnum.BARTLETT -> WindowFunction.Bartlett(uiState.numberOfTaps.value!!)
                WindowFunctionEnum.HANNING -> WindowFunction.Hanning(uiState.numberOfTaps.value!!)
                WindowFunctionEnum.HAMMING -> WindowFunction.Hamming(uiState.numberOfTaps.value!!)
                WindowFunctionEnum.BLACKMAN -> WindowFunction.Blackman(uiState.numberOfTaps.value!!)
            }
            val filter = when (filterType) {
                FilterTypeEnum.LOW_PASS ->
                    designLowPassFilter(
                        uiState.lowCutFrequency.value!!,
                        uiState.sampleRate.value!!,
                        uiState.numberOfTaps.value!!,
                        windowFunction
                    )

                FilterTypeEnum.HIGH_PASS ->
                    designHighPassFilter(
                        uiState.highCutFrequency.value!!,
                        uiState.sampleRate.value!!,
                        uiState.numberOfTaps.value!!,
                        windowFunction
                    )
//
//                FilterTypeEnum.BAND_PASS ->
//                    designBandPassFilter(
//                        uiState.lowCutFrequency.value!!,
//                        uiState.highCutFrequency.value!!,
//                        uiState.sampleRate.value!!,
//                        uiState.numberOfTaps.value!!,
//                        windowFunction
//                    )
//
//                FilterTypeEnum.BAND_REJECT ->
//                    designBandRejectFilter(
//                        uiState.lowCutFrequency.value!!,
//                        uiState.highCutFrequency.value!!,
//                        uiState.sampleRate.value!!,
//                        uiState.numberOfTaps.value!!,
//                        windowFunction
//                    )
            }

            println(filter)

            var impulseResponse by uiState.impulseResponse
            var frequencyResponse by uiState.frequencyResponse
            var frequencyResponseDb by uiState.frequencyResponseDb
            impulseResponse = filter.impulseResponse()
            frequencyResponse = filter.frequencyResponse(uiState.sampleRate.value!!)
            frequencyResponseDb = filter.frequencyResponseDb(uiState.sampleRate.value!!)
        }
    ) {
        Text("Calculate")
    }
}
