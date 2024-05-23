package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.DialogWindow
import state.sharedState

@Composable
fun ChooseFilterButtonWithDialog(
    enabled: Boolean,
    onSelect: () -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    val rememberedFilters by remember { sharedState.rememberedFilters }

    Button(
        enabled = enabled,
        onClick = { expanded = true }
    ) {
        Text("Выбрать фильтр")
    }
    DialogWindow(
        title = "Выбор фильтра",
        resizable = false,
        onCloseRequest = { expanded = false },
        visible = expanded,
        content = {
            Column(
                modifier = Modifier.fillMaxSize()
                    .background(color = Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                FilteringFilterList(
                    onSelect = {
                        onSelect()
                        expanded = false
                    },
                    filterNames = rememberedFilters.keys
                )
            }
        }
    )
}
