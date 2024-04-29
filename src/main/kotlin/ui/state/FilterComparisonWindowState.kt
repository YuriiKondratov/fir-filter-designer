package ui.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import core.FilterInfo

val filterComparisonWindowState = FilterComparisonWindowState()

class FilterComparisonWindowState {
    val rememberedFilters = mutableStateOf<Map<String, FilterInfo>>(mapOf())
    val chosenFilters = mutableStateOf<Set<String>>(setOf())
}

fun FilterComparisonWindowState.addFilter(name: String, filter: FilterInfo) {
    var rememberedFilters by rememberedFilters
    rememberedFilters = rememberedFilters.toMutableMap().let {
        it[name] = filter
        it
    }
}


fun FilterComparisonWindowState.chooseFilter(filterName: String) {
    var chosenFilters by chosenFilters
    chosenFilters = chosenFilters.toMutableSet().let {
        if (filterName in it) {
            it.remove(filterName)
        } else {
            it.add(filterName)
        }
        it
    }
}
