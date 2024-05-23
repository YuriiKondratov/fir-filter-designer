package controls

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import state.addFilter
import state.filterDesignWindowState
import state.sharedState

fun rememberFilter(filterName: String) {
    GlobalScope.launch {
        sharedState.addFilter(
            filterName,
            filterDesignWindowState.currentFilter.value
        )
    }
}
