package com.ericho.compose.demo.ui.custom

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ericho.compose.demo.util.drawFunnel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height

@Composable
fun SampleDrawDemo() {
    val particleRadiusDp = 12.dp
    val particleRadius: Float
    val itemHeightDp = 100.dp
    val itemHeight: Float
    with(LocalDensity.current) {
        particleRadius = particleRadiusDp.toPx()
        itemHeight = itemHeightDp.toPx()
    }
    val radius = itemHeight * 0.5f
    val funnelWidth = radius * 3
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.height(itemHeightDp)) {
            drawPath(
                path = drawFunnel(
                    upperRadius = radius,
                    lowerRadius = particleRadius * 3 / 4f,
                    width = funnelWidth
                ),
                color = Color.Red
            )
            drawCircle(color = Color(255, 200, 100, 255), radius = particleRadius)
        }
    }
}

@Preview
@Composable
fun SampleDrawDemoPreview() {
    SampleDrawDemo()
}