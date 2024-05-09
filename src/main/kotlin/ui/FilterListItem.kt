package ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.state.chooseFilter
import ui.state.deleteFilter
import ui.state.filterComparisonWindowState
import ui.state.sharedState

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
        BasicTextField(
            onValueChange = {},
            value = name,
            modifier = Modifier
                .padding(start = 10.dp)
                .weight(1F),
            maxLines = 1,
        )
        IconButton(
            onClick = { sharedState.deleteFilter(name) }
        ) {
            Icon(
                Icons.Rounded.Delete,
                contentDescription = "1234"
            )
        }
        RadioButton(
            selected = chosenFilters.contains(name),
            onClick = { filterComparisonWindowState.chooseFilter(name) }
        )
    }
}
