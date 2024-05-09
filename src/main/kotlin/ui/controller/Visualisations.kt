package ui.controller

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ui.state.chooseVisualizedPart
import ui.state.filtrationWindowState
import java.nio.BufferUnderflowException
import java.nio.ByteBuffer
import java.nio.ByteOrder
import javax.sound.sampled.AudioSystem

fun visualizeInputSignal() {
    GlobalScope.launch {
        val audioInput = AudioSystem.getAudioInputStream(filtrationWindowState.file.value)
        val sampleRate = audioInput.format.sampleRate.toInt()

        println(sampleRate)

        val startTime = filtrationWindowState.startTime.value!!
        val endTime = filtrationWindowState.endTime.value!!

        val startIndex = (startTime * sampleRate).toInt()
        val endIndex = (endTime * sampleRate).toInt()

        val byteArraySize = (endIndex - startIndex) * audioInput.format.frameSize
        val byteArray = ByteArray(byteArraySize) { 0 }
        audioInput.skipNBytes(startIndex.toLong())
        audioInput.read(byteArray)

        val byteOrder = if (audioInput.format.isBigEndian) ByteOrder.BIG_ENDIAN else ByteOrder.LITTLE_ENDIAN
        val signalValues = mutableListOf<Double>()
        val buffer = ByteBuffer.wrap(byteArray).order(byteOrder)
        while (signalValues.size < (endIndex - startIndex)) {
            try {
                signalValues.add(buffer.getShort().toDouble())
            } catch (ex: BufferUnderflowException) {
                break
            }
        }

        val step = (endTime - startTime) / signalValues.size
        filtrationWindowState.chooseVisualizedPart(
            signalValues.withIndex().associate { (i, x) ->
                (startTime + i * step) to signalValues[i]
            }
        )
    }
}

fun applyCurrentFilter() {
    GlobalScope.launch {

    }
}
