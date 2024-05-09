package ui.state

import androidx.compose.runtime.mutableStateOf

val bandPassFilterDesignState = BandPassFilterDesignState()

class BandPassFilterDesignState {
    val numberOfLowPassTaps = mutableStateOf<Int?>(null)
    val numberOfHighPassTaps = mutableStateOf<Int?>(null)
    val lowPassFrequency = mutableStateOf<Double?>(null)
    val highPassFrequency = mutableStateOf<Double?>(null)
}
