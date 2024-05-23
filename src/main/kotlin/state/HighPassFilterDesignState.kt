package state

import androidx.compose.runtime.mutableStateOf

val highPassFilterDesignState = HighPassFilterDesignState()

class HighPassFilterDesignState {
    val numberOfTaps = mutableStateOf<Int?>(null)
    val highPassFrequency = mutableStateOf<Double?>(null)
}
