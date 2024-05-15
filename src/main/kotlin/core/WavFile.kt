package core

import java.io.File
import java.nio.BufferUnderflowException
import java.nio.ByteBuffer
import java.nio.ByteOrder
import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.UnsupportedAudioFileException

class WavFile(private val file: File) {
    val sampleRate: Int
    val frameRate: Float
    val frameLength: Long
    val frameSize: Int
    val byteOrder: ByteOrder
    val duration: Float
    val name: String
    val format: AudioFormat

    init {
        AudioSystem.getAudioInputStream(file).use {
            frameLength = it.frameLength
            format = it.format
        }

        sampleRate = format.sampleRate.toInt()
        frameRate = format.frameRate
        frameSize = format.frameSize
        byteOrder = if (format.isBigEndian) {
            ByteOrder.BIG_ENDIAN
        } else {
            ByteOrder.LITTLE_ENDIAN
        }
        duration = frameLength.toFloat() / frameRate
        name = file.name

        if (frameSize != 2) {
            throw UnsupportedAudioFileException("поддерживаются только wav файлы с разрядностью 16 бит")
        }
    }

    fun getData(from: Long, to: Long): List<Short> {
        if (to <= from || to > frameLength) {
            return emptyList()
        }

        val dataSize = to - from
        val byteArraySize = dataSize * frameSize
        val byteArray: ByteArray

        AudioSystem.getAudioInputStream(file).use {
            it.skipNBytes(from * frameSize)
            byteArray = it.readNBytes(byteArraySize.toInt())
        }

        val data = mutableListOf<Short>()
        val buffer = ByteBuffer.wrap(byteArray).order(byteOrder)
        while (data.size < dataSize) {
            try {
                data.add(buffer.getShort())
            } catch (ex: BufferUnderflowException) {
                break
            }
        }
        return data
    }
}
