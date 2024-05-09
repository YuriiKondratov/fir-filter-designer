package ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.state.appWindowsState
import ui.state.isExitNeeded

fun FirFilterDesignerApp() = application {
    var designWindowVisible by remember { appWindowsState.designWindowVisible }
    var comparisonWindowVisible by remember { appWindowsState.comparisonWindowVisible }
    var filteringWindowVisible by remember { appWindowsState.filteringWindowVisible }

    MaterialTheme {
        Window(
            title = "Проектирование",
            visible = designWindowVisible,
            onCloseRequest = {
                designWindowVisible = false
                if (appWindowsState.isExitNeeded()) {
                    exitApplication()
                }
            }
        ) {
            AppMenuBar()
            FilterDesignPanel()
        }
        Window(
            title = "Сравнение",
            visible = comparisonWindowVisible,
            onCloseRequest = {
                comparisonWindowVisible = false
                if (appWindowsState.isExitNeeded()) {
                    exitApplication()
                }
            }
        ) {
            AppMenuBar()
            FilterComparisonPanel()
        }
        Window(
            title = "Применение",
            visible = filteringWindowVisible,
            onCloseRequest = {
                filteringWindowVisible = false
                if (appWindowsState.isExitNeeded()) {
                    exitApplication()
                }
            }
        ) {
            AppMenuBar()
            FiltrationPanel()
        }
    }
}
