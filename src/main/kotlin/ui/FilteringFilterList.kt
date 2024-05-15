package ui

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import ui.state.chooseFilter
import ui.state.filteringWindowState

@Composable
fun FilteringFilterList(
    filterNames: Set<String>,
    onSelect: () -> Unit
) {
    val chosenFilter by remember { filteringWindowState.chosenFilter }

    Box(
        modifier = Modifier.fillMaxSize()
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
                            onSelect = {
                                onSelect()
                                filteringWindowState.chooseFilter(it)
                            },
                            selected = chosenFilter?.equals(it) ?: false
                        ) {}
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
