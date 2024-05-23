package state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import core.FilterInfo

val sharedState = SharedState()

class SharedState {
    val rememberedFilters = mutableStateOf<Map<String, FilterInfo>>(mapOf())
}

fun SharedState.addFilter(name: String, filter: FilterInfo) {
    var rememberedFilters by rememberedFilters
    rememberedFilters = rememberedFilters.toMutableMap().let {
        it[name] = filter
        it
    }
}


fun SharedState.deleteFilter(filterName: String) {
    var rememberedFilters by rememberedFilters
    rememberedFilters = rememberedFilters.toMutableMap().let {
        it.remove(filterName)
        it
    }
}

fun SharedState.getFilter(filerName: String) =
    rememberedFilters.value[filerName]
