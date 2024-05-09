package ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar
import ui.state.appWindowsState

@Composable
fun FrameWindowScope.AppMenuBar() {
    var designWindowVisible by remember { appWindowsState.designWindowVisible }
    var comparisonWindowVisible by remember { appWindowsState.comparisonWindowVisible }
    var filteringWindowVisible by remember { appWindowsState.filteringWindowVisible }

    MenuBar {
        Menu("Меню") {
            Item("Проектирование", onClick = {
                designWindowVisible = true
            })
            Item("Сравнение", onClick = {
                comparisonWindowVisible = true
            })
            Item("Применение", onClick = {
                filteringWindowVisible = true
            })
        }
    }
}