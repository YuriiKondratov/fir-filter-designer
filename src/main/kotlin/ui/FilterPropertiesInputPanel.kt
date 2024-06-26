package ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.unit.dp
import core.FilterTypeEnum
import controls.calculateBandPassFilter
import controls.calculateBandRejectFilter
import controls.calculateHighPassFilter
import controls.calculateLowPassFilter
import controls.validateBandPassFilterData
import controls.validateBandRejectFilterData
import controls.validateHighPassFilterData
import controls.validateLowPassFilterData
import ui.input.IntInput
import state.filterDesignWindowState
import java.awt.FileDialog
import java.io.File
import java.nio.file.Files
import kotlin.math.round

@Composable
fun FilterPropertiesInputPanel() {
    val filterType by remember { filterDesignWindowState.filterType }
    var sampleRate by remember { filterDesignWindowState.sampleRate }

    var errorMessage by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    ErrorMessageDialog(
        isError,
        errorMessage
    ) {
        isError = false
    }

    Column(
        modifier = Modifier
            .width(300.dp)
            .fillMaxHeight()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {

        IntInput(
            value = sampleRate,
            onValueChange = { sampleRate = it },
            label = { Text("Частота дискретизации, Гц") }
        )
        WindowFunctionMenu()
        FilterTypeMenu()

        val calculationCommand: () -> Unit = when (filterType) {
            FilterTypeEnum.LOW_PASS -> {
                LowPassFilterDesignPanel();
                {
                    val error = validateLowPassFilterData()
                    error?.run {
                        errorMessage = this
                        isError = true
                    } ?: calculateLowPassFilter()
                }
            }

            FilterTypeEnum.HIGH_PASS -> {
                HighPassFilterDesignPanel();
                {
                    val error = validateHighPassFilterData()
                    error?.run {
                        errorMessage = this
                        isError = true
                    } ?: calculateHighPassFilter()
                }
            }

            FilterTypeEnum.BAND_PASS -> {
                BandPassFilterDesignPanel();
                {
                    val error = validateBandPassFilterData()
                    error?.run {
                        errorMessage = this
                        isError = true
                    } ?: calculateBandPassFilter()
                }
            }

            FilterTypeEnum.BAND_REJECT -> {
                BandRejectFilterDesignPanel();
                {
                    val error = validateBandRejectFilterData()
                    error?.run {
                        errorMessage = this
                        isError = true
                    } ?: calculateBandRejectFilter()
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = calculationCommand
                ) {
                    Text("Рассчитать")
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RememberButtonWithDialog(
                    onClick = {
                        val filter = filterDesignWindowState.currentFilter.value
                        if (filter.impulseResponse.isEmpty()) {
                            errorMessage = "Необходимо сначала рассчитать фильтр!"
                            isError = true
                            false
                        } else {
                            true
                        }
                    }
                )
                InfoIconWithTooltip(
                    "Запомненные фильтры будут доступны\nдля сравнения " +
                            "в окне 'Сравнение'\nи для выбора в окне 'Применение'",
                    alignment = Alignment.TopCenter
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        val filter = filterDesignWindowState.currentFilter.value
                        if (filter.impulseResponse.isEmpty()) {
                            errorMessage = "Необходимо сначала рассчитать фильтр!"
                            isError = true
                        } else {
                            val dialog = FileDialog(ComposeWindow(), "", FileDialog.SAVE)
                            dialog.isVisible = true
                            if (dialog.directory != null || dialog.file != null) {
                                val path = dialog.directory + dialog.file + ".txt"
                                val file = File(path)
                                Files.deleteIfExists(file.toPath())
                                try {
                                    file.writeText(filter.impulseResponse
                                        .values
                                        .map { round(it * 10e6) / 10e6 }
                                        .joinToString { it.toBigDecimal().toPlainString() })
                                } catch (ex: Exception) {
                                    errorMessage = "Ошибка при записи файла: ${ex.message}"
                                    isError = true
                                }
                            }
                        }
                    }
                ) {
                    Text("Экспортировать")
                }
                InfoIconWithTooltip(
                    "Сохранить коэффициенты фильтра\nв текстовый файл",
                    alignment = Alignment.TopStart
                )
            }
        }
    }
}
