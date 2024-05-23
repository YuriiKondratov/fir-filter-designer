package state

import androidx.compose.runtime.mutableStateOf

val bandRejectFilterDesignState = BandRejectFilterDesignState()

class BandRejectFilterDesignState {
    val numberOfLowPassTaps = mutableStateOf<Int?>(null)
    val numberOfHighPassTaps = mutableStateOf<Int?>(null)
    val lowPassFrequency = mutableStateOf<Double?>(null)
    val highPassFrequency = mutableStateOf<Double?>(null)
}
