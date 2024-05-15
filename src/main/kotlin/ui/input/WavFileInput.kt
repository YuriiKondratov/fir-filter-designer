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
    onChoice: (WavFile?) -> Unit,
    onError: (Throwable) -> Unit = {}
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
                val file = try {
                    WavFile(File(path))
                } catch (ex: Throwable) {
                    onError(ex)
                    null
                }
                onChoice(file)
            }
        }
    ) {
        Text("Выбрать .wav файл")
    }
}
