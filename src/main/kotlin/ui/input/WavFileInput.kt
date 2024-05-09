package ui.input

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.awt.ComposeWindow
import java.awt.FileDialog
import java.io.File

@Composable
fun WavFileInput(
    onChoice: (File) -> Unit
) {
    Button(
        onClick = {
            val dialog = FileDialog(ComposeWindow(), "", FileDialog.LOAD)
            dialog.setFilenameFilter { _, name -> name.endsWith(".wav") }
            dialog.isVisible = true
            val path = dialog.directory + dialog.file
            onChoice(File(path))
        }
    ) {
        Text("Choose file")
    }
}
