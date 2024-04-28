package ui

import FilterVisualizationPanel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun FilterDesignPanel() {
    Row(
        modifier = Modifier
            .background(color = Color.White)
    ) {
        FilterPropertiesInputPanel()
        FilterVisualizationPanel()
    }
}
