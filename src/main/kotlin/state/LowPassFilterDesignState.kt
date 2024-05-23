package state

import androidx.compose.runtime.mutableStateOf

val lowPassFilterDesignState = LowPassFilterDesignState()

class LowPassFilterDesignState {
    val numberOfTaps = mutableStateOf<Int?>(null)
    val lowPassFrequency = mutableStateOf<Double?>(null)
}
