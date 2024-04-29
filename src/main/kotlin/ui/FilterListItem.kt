package ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ui.state.chooseFilter
import ui.state.filterComparisonWindowState

@Composable
fun FilterListItem(
    name: String
) {
    val chosenFilters by remember { filterComparisonWindowState.chosenFilters }

    Row(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(3.dp)
            ).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp),
            maxLines = 1,
            text = name
        )
        RadioButton(
            selected = chosenFilters.contains(name),
            onClick = {
                filterComparisonWindowState.chooseFilter(name)
            }
        )
    }
}
