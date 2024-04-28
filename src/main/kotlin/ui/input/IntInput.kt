package ui.input

import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun IntInput(
    onValueChange: (Int?) -> Unit,
    value: Int? = null,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null
) {
    var textFieldValue by remember { mutableStateOf(value?.toString() ?: "") }

    TextField(
        value = textFieldValue,
        singleLine = true,
        label = label,
        modifier = modifier,
        onValueChange = { newValue ->
            val intValue = newValue.toIntOrNull()
            if (intValue != null || newValue.isEmpty()) {
                textFieldValue = newValue.trimStart('0')
                onValueChange(intValue)
            }
        }
    )
}