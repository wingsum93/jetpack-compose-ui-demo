package com.ericho.compose.demo.ui.custom.fluidsider

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height

@Composable
fun FluidSliderDemo() {
    Column(Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(100.dp))
        FluidSlider()
    }
}

@Preview
@Composable
fun FluidSliderDemoPreview() {
    FluidSliderDemo()
}