package com.ericho.compose.demo.ui.touch

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.*
import kotlin.math.roundToInt

@ExperimentalGraphicsApi
@Composable
fun GestureAnimationPage1() {
    val circleColor = Color.hsl(200f, 0.5f, 0.5f)
    val offset = remember { Animatable(Offset(0f, 0f), Offset.VectorConverter) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                coroutineScope {
                    while (true) {
                        // Detect a tap event and obtain its position.
                        val position = awaitPointerEventScope {
                            awaitFirstDown().position
                        }
                        launch {
                            // Animate to the tap position.
                            offset.animateTo(
                                position,
                                tween(1500), initialVelocity = Offset(0f, 0f)
                            )
                        }
                    }
                }
            }
    ) {
        Circle(modifier = Modifier.offset { offset.value.toIntOffset() }, color = circleColor)
    }
}

@Composable
fun Circle(modifier: Modifier = Modifier, color: Color = Color.Blue) {
    modifier.widthIn(20.dp, 200.dp)
    modifier.heightIn(20.dp, 200.dp)
    Canvas(modifier = modifier
        .size(150.dp), onDraw = {
        val (width, height) = this.size

        drawCircle(
            color = color,
            center = Offset(0f, 0f)
        )

    })
}

internal fun Offset.toIntOffset() = IntOffset(x.roundToInt(), y.roundToInt())

@Preview("GestureAnimationPage1")
@ExperimentalGraphicsApi
@Composable
fun GestureAnimationPage1Preview() {
    GestureAnimationPage1()
}

@Preview("CirclePreview")
@Composable
fun CirclePreview() {
    Circle()
}