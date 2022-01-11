package com.ericho.compose.demo.ui.custom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

@Composable
fun FluidSliderDemo() {
    Column(Modifier.fillMaxSize()) {
        FluidSlider()
    }
}

@Preview
@Composable
fun FluidSliderDemoPreview() {
    FluidSliderDemo()
}