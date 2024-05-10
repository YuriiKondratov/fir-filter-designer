package ui.controller

import core.WindowFunction
import core.WindowFunctionType
import core.calculateInfo
import core.designBandPassFilter
import core.designBandRejectFilter
import core.designHighPassFilter
import core.designLowPassFilter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ui.state.bandPassFilterDesignState
import ui.state.bandRejectFilterDesignState
import ui.state.filterDesignWindowState
import ui.state.highPassFilterDesignState
import ui.state.lowPassFilterDesignState
import ui.state.setCurrentFilter

fun calculateLowPassFilter() {
    GlobalScope.launch {
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
}

fun calculateHighPassFilter() {
    GlobalScope.launch {
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
}

fun calculateBandPassFilter() {
    GlobalScope.launch {
        val numberOfLowPassTaps = bandPassFilterDesignState.numberOfLowPassTaps.value!!
        val numberOfHighPassTaps = bandPassFilterDesignState.numberOfHighPassTaps.value!!
        val sampleRate = filterDesignWindowState.sampleRate.value!!
        val filter = designBandPassFilter(
            bandPassFilterDesignState.lowPassFrequency.value!!,
            bandPassFilterDesignState.highPassFrequency.value!!,
            numberOfLowPassTaps,
            numberOfHighPassTaps,
            calculateWindow(filterDesignWindowState.windowFunction.value, numberOfLowPassTaps),
            calculateWindow(filterDesignWindowState.windowFunction.value, numberOfHighPassTaps),
            sampleRate
        )
        filterDesignWindowState.setCurrentFilter(
            filter.calculateInfo(sampleRate)
        )
    }
}

fun calculateBandRejectFilter() {
    GlobalScope.launch {
        val numberOfLowPassTaps = bandRejectFilterDesignState.numberOfLowPassTaps.value!!
        val numberOfHighPassTaps = bandRejectFilterDesignState.numberOfHighPassTaps.value!!
        val sampleRate = filterDesignWindowState.sampleRate.value!!
        val filter = designBandRejectFilter(
            bandRejectFilterDesignState.lowPassFrequency.value!!,
            bandRejectFilterDesignState.highPassFrequency.value!!,
            numberOfLowPassTaps,
            numberOfHighPassTaps,
            calculateWindow(filterDesignWindowState.windowFunction.value, numberOfLowPassTaps),
            calculateWindow(filterDesignWindowState.windowFunction.value, numberOfHighPassTaps),
            sampleRate
        )
        filterDesignWindowState.setCurrentFilter(
            filter.calculateInfo(sampleRate)
        )
    }
}

private fun calculateWindow(type: WindowFunctionType, size: Int): WindowFunction =
    when (type) {
        WindowFunctionType.RECTANGULAR -> WindowFunction.Rectangular(size)
        WindowFunctionType.BARTLETT -> WindowFunction.Bartlett(size)
        WindowFunctionType.HANNING -> WindowFunction.Hanning(size)
        WindowFunctionType.HAMMING -> WindowFunction.Hamming(size)
        WindowFunctionType.BLACKMAN -> WindowFunction.Blackman(size)
    }
