package com.ericho.compose.demo.ui.custom

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*

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
    Column(
        modifier
            .padding(10.dp, 50.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeightIn(50.dp, 150.dp)
        ) {
            Canvas(modifier = Modifier
                .fillMaxSize()
                .pointerInput(start_text, end_text, duration) {
                    detectHorizontalDragGestures { change, dragAmount ->

                    }
                }) {
                drawRoundRect(color = bar_color)
                //
                val path = Path()
                path.addArc(Rect(0f, 100f, 200f, 300f), 270f, 180f)
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