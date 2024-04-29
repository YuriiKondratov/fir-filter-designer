package ui.controller

import ui.state.addFilter
import ui.state.filterComparisonWindowState
import ui.state.filterDesignWindowState

fun rememberFilter(filterName: String) {
    filterComparisonWindowState.addFilter(
        filterName,
        filterDesignWindowState.currentFilter.value
    )
}
