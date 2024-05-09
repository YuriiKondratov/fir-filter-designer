package ui.controller

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ui.state.addFilter
import ui.state.filterDesignWindowState
import ui.state.sharedState

fun rememberFilter(filterName: String) {
    GlobalScope.launch {
        sharedState.addFilter(
            filterName,
            filterDesignWindowState.currentFilter.value
        )
    }
}
