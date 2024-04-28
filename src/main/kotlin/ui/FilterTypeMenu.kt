package ui

import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import core.FilterTypeEnum

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterTypeMenu() {
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { uiState.filterType }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            value = selected.description,
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            enumValues<FilterTypeEnum>().forEach { type ->
                DropdownMenuItem(
                    onClick = {
                        selected = type
                        expanded = !expanded
                    }
                ) {
                    Text(type.description)
                }
            }
        }
    }
}