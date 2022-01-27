package com.ericho.compose.demo.ui.custom.fluidsider

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeightIn

@Composable
fun FluidSlider(
//    size: Size
) {
    FluidSliderImpl()
}

@Composable
fun FluidSliderImpl(
    start_text: String = "",
    end_text: String = "",
    duration: Long = 1000,
    bar_color: Color = Color.Blue,
    bubble_color: Color = Color.White,
    bar_text_color: Color = Color.Black,
    bubble_text_color: Color = Color.Black,
    text_size: Size = Size.NORMAL,
    // range from 0f ... 1f
    initial_position: Float = 0f,
    size: Size = Size.NORMAL,
    modifier: Modifier = Modifier
) {
    val begainTractListener: () -> Unit = {}
    val endTrackingListener: () -> Unit = {}
    with(LocalDensity.current) {
        12.dp.toPx()
    }
    val BAR_CORNER_RADIUS = 2
    val BAR_VERTICAL_OFFSET = 1.5f
    val BAR_INNER_HORIZONTAL_OFFSET = 0
    var position: Float by remember { mutableStateOf(0.5f) }
    val barHeight: Dp = if (size == Size.NORMAL) 20.dp else 15.dp
    val SLIDER_HEIGHT = 40f

    Box(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeightIn(50.dp, 150.dp)
    ) {
        Canvas(modifier = Modifier
            .fillMaxSize()
            .pointerInput(start_text, end_text, duration) {
                detectHorizontalDragGestures { change, dragAmount ->

                }
            }) {
            val (canvasWidth, canvasHeight) = this.size
            var zeroPoint = Offset.Zero
            zeroPoint += Offset(10f, canvasHeight / 2)
            drawRoundRect(
                color = bar_color, topLeft = zeroPoint,
                size = androidx.compose.ui.geometry.Size(canvasWidth, SLIDER_HEIGHT),
                cornerRadius = CornerRadius(
                    50f, 50f
                )
            )
            //draw blue outline blue circle
//            drawCircle()
            //draw inner small white circle

            //
            drawIntoCanvas {
                val paint = android.graphics.Paint()
                paint.textAlign = Paint.Align.CENTER
                paint.textSize = 64f
                paint.color = 0xffff0000.toInt()
                it.nativeCanvas.drawText("Hello", center.x, center.y, paint)
            }
        }
    }
}

object FluidSliderConstant {
    const val BAR_CORNER_RADIUS = 2
    const val BAR_VERTICAL_OFFSET = 1.5f
    const val BAR_INNER_HORIZONTAL_OFFSET = 0

    const val SLIDER_WIDTH = 4
    const val SLIDER_HEIGHT = 1 + BAR_VERTICAL_OFFSET

    const val TOP_CIRCLE_DIAMETER = 1
    const val BOTTOM_CIRCLE_DIAMETER = 25.0f
    const val TOUCH_CIRCLE_DIAMETER = 1
    const val LABEL_CIRCLE_DIAMETER = 10

    const val ANIMATION_DURATION = 400
    const val TOP_SPREAD_FACTOR = 0.4f
    const val BOTTOM_START_SPREAD_FACTOR = 0.25f
    const val BOTTOM_END_SPREAD_FACTOR = 0.1f
    const val METABALL_HANDLER_FACTOR = 2.4f
    const val METABALL_MAX_DISTANCE = 15.0f
    const val METABALL_RISE_DISTANCE = 1.1f

    const val TEXT_SIZE = 12
    const val TEXT_OFFSET = 8
    const val TEXT_START = "0"
    const val TEXT_END = "100"

    const val COLOR_BAR = 0xff6168e7.toInt()
    const val COLOR_LABEL = android.graphics.Color.WHITE
    const val COLOR_LABEL_TEXT = android.graphics.Color.BLACK
    const val COLOR_BAR_TEXT = android.graphics.Color.WHITE

    const val INITIAL_POSITION = 0.5f
}

enum class Size(val value: Int) {
    /**
     * Default size - 56dp.
     */
    NORMAL(56),

    /**
     * Small size - 40dp.
     */
    SMALL(40)
}

@Preview
@Composable
fun FluidSliderPreview() {
    FluidSlider()
}