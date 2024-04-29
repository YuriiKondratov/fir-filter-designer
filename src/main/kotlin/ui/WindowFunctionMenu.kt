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
import core.WindowFunctionType
import ui.state.filterDesignWindowState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WindowFunctionMenu() {
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { filterDesignWindowState.windowFunction }

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
            enumValues<WindowFunctionType>().forEach { type ->
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