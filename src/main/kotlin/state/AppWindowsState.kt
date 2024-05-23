package state

import androidx.compose.runtime.mutableStateOf

val appWindowsState = AppWindowsState()

class AppWindowsState {
    val designWindowVisible = mutableStateOf(true)
    val comparisonWindowVisible = mutableStateOf(false)
    val filteringWindowVisible = mutableStateOf(false)
}

fun AppWindowsState.isExitNeeded() =
    !designWindowVisible.value &&
    !comparisonWindowVisible.value &&
    !filteringWindowVisible.value
