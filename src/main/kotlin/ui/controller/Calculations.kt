package ui.controller

import core.WindowFunction
import core.WindowFunctionType
import core.calculateInfo
import core.designHighPassFilter
import core.designLowPassFilter
import ui.state.filterDesignWindowState
import ui.state.highPassFilterDesignState
import ui.state.lowPassFilterDesignState
import ui.state.setCurrentFilter


fun calculateLowPassFilter() {
    val numberOfTaps = lowPassFilterDesignState.numberOfTaps.value!!
    val sampleRate = filterDesignWindowState.sampleRate.value!!
    val filter = designLowPassFilter(
        sampleRate,
        lowPassFilterDesignState.lowPassFrequency.value!!,
        numberOfTaps,
        calculateWindow(filterDesignWindowState.windowFunction.value, numberOfTaps)
    )
    filterDesignWindowState.setCurrentFilter(
        filter.calculateInfo(sampleRate)
    )
}

fun calculateHighPassFilter() {
    val numberOfTaps = highPassFilterDesignState.numberOfTaps.value!!
    val sampleRate = filterDesignWindowState.sampleRate.value!!
    val filter = designHighPassFilter(
        sampleRate,
        highPassFilterDesignState.highPassFrequency.value!!,
        numberOfTaps,
        calculateWindow(filterDesignWindowState.windowFunction.value, numberOfTaps)
    )
    filterDesignWindowState.setCurrentFilter(
        filter.calculateInfo(sampleRate)
    )
}

private fun calculateWindow(type: WindowFunctionType, size: Int): WindowFunction =
    when (type) {
        WindowFunctionType.RECTANGULAR -> WindowFunction.Rectangular(size)
        WindowFunctionType.BARTLETT -> WindowFunction.Bartlett(size)
        WindowFunctionType.HANNING -> WindowFunction.Hanning(size)
        WindowFunctionType.HAMMING -> WindowFunction.Hamming(size)
        WindowFunctionType.BLACKMAN -> WindowFunction.Blackman(size)
    }
