package state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import core.FilterInfo
import core.FilterTypeEnum
import core.WindowFunctionType

val filterDesignWindowState = FilterDesignWindowState()

class FilterDesignWindowState {
    val sampleRate = mutableStateOf<Int?>(null)
    val windowFunction = mutableStateOf(WindowFunctionType.RECTANGULAR)
    val filterType = mutableStateOf(FilterTypeEnum.LOW_PASS)
    val currentFilter = mutableStateOf(
        FilterInfo(mapOf(), mapOf(), mapOf())
    )
}

fun FilterDesignWindowState.setCurrentFilter(filter: FilterInfo) {
    var currentFilter by currentFilter
    currentFilter = filter
}
