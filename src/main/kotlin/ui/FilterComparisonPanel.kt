package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ui.state.filterComparisonWindowState

@Composable
fun FilterComparisonPanel() {
    val chosen by remember { filterComparisonWindowState.chosenFilters }
    val rememberedFilters by remember { filterComparisonWindowState.rememberedFilters }

    Row(
        modifier = Modifier
            .background(color = Color.White)
    ) {
        FilterList(
            rememberedFilters.keys
        )
        FilterComparisonVisualizationPanel(
            rememberedFilters.filter { it.key in chosen }
        )
    }
}
