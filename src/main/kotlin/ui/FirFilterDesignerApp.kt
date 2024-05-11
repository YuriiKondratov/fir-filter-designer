package ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ui.state.appWindowsState
import ui.state.isExitNeeded

fun FirFilterDesignerApp() = application {
    var designWindowVisible by remember { appWindowsState.designWindowVisible }
    var comparisonWindowVisible by remember { appWindowsState.comparisonWindowVisible }
    var filteringWindowVisible by remember { appWindowsState.filteringWindowVisible }

    MaterialTheme {
        Window(
            state = rememberWindowState(size = DpSize(1000.dp, 700.dp)),
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
            state = rememberWindowState(size = DpSize(1200.dp, 800.dp)),
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
            state = rememberWindowState(size = DpSize(1000.dp, 600.dp)),
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
            FilteringPanel()
        }
    }
}
