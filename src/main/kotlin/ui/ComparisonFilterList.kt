package ui

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import ui.state.chooseFilter
import ui.state.deleteFilter
import ui.state.filterComparisonWindowState
import ui.state.sharedState

@Composable
fun ComparisonFilterList(
    filterNames: Set<String>
) {
    val chosenFilters by remember { filterComparisonWindowState.chosenFilters }

    Box(
        modifier = Modifier
            .width(300.dp)
    ) {
        val stateVertical = rememberScrollState(0)

        Box(
            modifier = Modifier
                .verticalScroll(stateVertical)
                .padding(10.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                if (filterNames.isEmpty()) {
                    Text("Список пуст. Для добавления фильтров нажмите \"Запомнить\" в окне \"Проектирование\"")
                } else {
                    filterNames.forEach {
                        FilterListItem(
                            name = it,
                            onSelect = { filterComparisonWindowState.chooseFilter(it) },
                            selected = chosenFilters.contains(it)
                        ) {
                            IconButton(
                                onClick = { sharedState.deleteFilter(it) }
                            ) {
                                Icon(
                                    Icons.Rounded.Delete,
                                    contentDescription = "1234"
                                )
                            }
                        }
                    }
                }
            }
        }
        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd)
                .fillMaxHeight(),
            adapter = rememberScrollbarAdapter(stateVertical)
        )
    }
}
