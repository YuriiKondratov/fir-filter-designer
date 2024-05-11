package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogWindow
import ui.controller.rememberFilter

@Composable
fun RememberButtonWithDialog() {
    var expanded by remember { mutableStateOf(false) }
    var filterName by remember { mutableStateOf("") }

    Button(
        onClick = { expanded = true }
    ) {
        Text("Запомнить")
    }
    DialogWindow(
        title = "Запомнить",
        resizable = false,
        onCloseRequest = { expanded = false },
        visible = expanded,
        content = {
            Column(
                modifier = Modifier.fillMaxSize().background(color = Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text("Введите имя фильтра:")
                TextField(
                    value = filterName,
                    singleLine = true,
                    label = { Text("Имя фильтра") },
                    onValueChange = { newValue ->
                        filterName = newValue
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = { expanded = false },
                    ) {
                        Text("Отмена")
                    }
                    Button(
                        onClick = {
                            rememberFilter(filterName)
                            expanded = false
                            filterName = ""
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colors.secondary
                        )
                    ) {
                        Text("Запомнить")
                    }
                }
            }
        }
    )
}
