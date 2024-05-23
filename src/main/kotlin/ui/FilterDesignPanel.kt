package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import state.filterDesignWindowState

@Composable
fun FilterDesignPanel() {
    val currentFilter by remember { filterDesignWindowState.currentFilter }

    Row(
        modifier = Modifier
            .background(color = Color.White)
    ) {
        FilterPropertiesInputPanel()
        FilterVisualizationPanel(currentFilter)
    }
}
