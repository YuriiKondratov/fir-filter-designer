package ui.input

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.awt.ComposeWindow
import core.WavFile
import java.awt.FileDialog
import java.io.File

@Composable
fun WavFileInput(
    onChoice: (WavFile?) -> Unit
) {
    Button(
        onClick = {
            val dialog = FileDialog(ComposeWindow(), "", FileDialog.LOAD)
            dialog.setFilenameFilter { _, name -> name.endsWith(".wav") }
            dialog.isVisible = true
            if (dialog.directory == null || dialog.file == null) {
                onChoice(null)
            } else {
                val path = dialog.directory + dialog.file
                onChoice(WavFile(File(path)))
            }
        }
    ) {
        Text("Выбрать .wav файл")
    }
}
