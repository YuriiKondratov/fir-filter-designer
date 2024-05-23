package state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

val filterComparisonWindowState = FilterComparisonWindowState()

class FilterComparisonWindowState {
    val chosenFilters = mutableStateOf<Set<String>>(setOf())
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
