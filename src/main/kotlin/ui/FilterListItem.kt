package ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FilterListItem(
    name: String,
    selected: Boolean,
    onSelect: () -> Unit,
    trailingContent: @Composable () -> Unit,
) {
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
        trailingContent()
        RadioButton(
            selected = selected,
            onClick = onSelect
        )
    }
}
