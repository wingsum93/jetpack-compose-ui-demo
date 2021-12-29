package com.ericho.compose.demo.ui.animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*

@Composable
fun AnimateColorPage2() {
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(scrollState)
    ) {
        //Part 1
        Text(text = "Infinite Animation")
        val targetTimelineX = 3000
        val targetTimelineY = 1000
        val infiniteTransition = rememberInfiniteTransition()

        val offsetX by infiniteTransition.animateValue(
            initialValue = -200, targetValue = 200,
            animationSpec = infiniteRepeatable(
                animation = tween(targetTimelineX, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
            typeConverter = Int.Companion.VectorConverter
        )
        val offsetY by infiniteTransition.animateValue(
            initialValue = -200, targetValue = 200,
            animationSpec = infiniteRepeatable(
                animation = tween(targetTimelineY, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
            typeConverter = Int.Companion.VectorConverter
        )
        val color1 by infiniteTransition.animateColor(
            initialValue = Color.Red, targetValue = Color.Green,
            animationSpec = infiniteRepeatable(
                animation = tween(targetTimelineX, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        ) {
            Surface(
                color = color1, shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .size(20.dp, 20.dp)
                    .align(Alignment.Center)
                    .offset(offsetX.dp, offsetY.dp)
            ) {
            }
        }
        Row() {

        }
    }

}

@Preview("AnimateColorPage2")
@Composable
fun AnimateColorPage2Preview() {
    AnimateColorPage2()
}