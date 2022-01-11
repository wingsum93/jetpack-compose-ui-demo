package com.ericho.compose.demo.ui.custom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn

@Composable
fun FluidSliderNativeDemo() {
    Column(Modifier.fillMaxSize()) {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 200.dp),
            factory = { context ->
                val slider = com.ramotion.fluidslider.FluidSlider(context = context)
                val min = 0
                val max = 1000
                val total = max - min
                slider.positionListener =
                    { pos -> slider.bubbleText = "${min + (total * pos).toInt()}" }
                slider.position = 0.3f
                slider.startText = "$min"
                slider.endText = "$max"
                slider
            }, update = {

            }
        )
    }
}

@Preview
@Composable
fun FluidSliderNativeDemoPreview() {
    FluidSliderNativeDemo()
}