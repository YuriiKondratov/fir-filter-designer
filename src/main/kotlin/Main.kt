import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.FilterComparisonPanel
import ui.FilterDesignPanel

fun main() = application {
    Window(
        title = "Design",
        onCloseRequest = ::exitApplication
    ) {
        MaterialTheme {
            FilterDesignPanel()
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
}
