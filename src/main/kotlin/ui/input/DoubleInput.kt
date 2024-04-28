package ui.input

import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun DoubleInput(
    onValueChange: (Double?) -> Unit,
    value: Double? = null,
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
            val doubleValue = newValue.toDoubleOrNull()
            if (doubleValue != null || newValue.isEmpty()) {
                textFieldValue = newValue.trimStart('0')
                onValueChange(doubleValue)
            }
        }
    )
}
