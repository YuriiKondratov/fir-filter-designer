import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.FilterComparisonPanel
import ui.FilterDesignPanel
import ui.FiltrationPanel

fun main() = application {
    Window(
        title = "Filtration",
        onCloseRequest = ::exitApplication
    ) {
        MaterialTheme {
            FiltrationPanel()
        }
    }
    Window(
        title = "Comparison",
        onCloseRequest = ::exitApplication
    ){
        MaterialTheme {
            FilterComparisonPanel()
        }
    }
    Window(
        title = "Design",
        onCloseRequest = ::exitApplication
    ) {
        MaterialTheme {
            FilterDesignPanel()
        }
    }
}
