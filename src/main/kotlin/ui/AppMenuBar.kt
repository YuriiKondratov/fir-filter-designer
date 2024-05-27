package ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar
import state.appWindowsState

@Composable
fun FrameWindowScope.AppMenuBar() {
    var designWindowVisible by remember { appWindowsState.designWindowVisible }
    var comparisonWindowVisible by remember { appWindowsState.comparisonWindowVisible }
    var filteringWindowVisible by remember { appWindowsState.filteringWindowVisible }
    var helpWindowVisible by remember { appWindowsState.helpWindowVisible }

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
            Item("Помощь", onClick = {
                helpWindowVisible = true
            })
        }
    }
}
