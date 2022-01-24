package com.ericho.compose.demo.ui.custom

import android.graphics.Paint
import android.graphics.RectF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ericho.compose.demo.R
import com.ericho.compose.demo.ui.theme.JetpackComposeUiDemoTheme
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.sizeIn
import kotlin.math.min

@Composable
fun BoardwayPieChart(
    modifier: Modifier = Modifier,
    // 0..360
    outlineProgressAngle: Float = 0f,
    // 0..360
    imageAngle: Float = 0f,
    separatorAngle: Float = 20f,
    circleOutlineColor: Color = Color(65, 236, 182, 255)
) {

    val outlineSafeAngle =
        remember(outlineProgressAngle) { outlineProgressAngle.coerceIn(0f..360f) }
    val imageSafeAngle = remember(imageAngle) { imageAngle.coerceIn(0f..360f) }
    val separatorSafeAngle = remember(separatorAngle) { separatorAngle.coerceIn(10f..60f) }
    //calculate sector of 3 arc
    val imageBitmap: ImageBitmap = ImageBitmap.imageResource(id = R.drawable.boardway_shape)

    Canvas(
        modifier = modifier
            .aspectRatio(1f)
            .sizeIn(
                minWidth = 300.dp,
                minHeight = 300.dp,
                maxHeight = 500.dp,
                maxWidth = 500.dp
            )
            .clip(CircleShape),
        onDraw = {
            //data part
            val width = this.size.width
            val height = this.size.height
            val strokeWidth = 150.dp.value
            val minLength = min(this.size.width - strokeWidth, size.height - strokeWidth)
            val leftOffset = (width - minLength) / 2
            val topOffset = (height - minLength) / 2
            val rect = RectF(leftOffset, topOffset, width - leftOffset, height - topOffset)

            val angleOffset = -90f
            val angle1MidPoint = 0 + angleOffset
            val angle2MidPoint = 120 + angleOffset
            val angle3MidPoint = 240 + angleOffset
            val smallAngleOffset = separatorSafeAngle / 2

            val maximiumSweepAngle = 360 / 3 - separatorSafeAngle
            val aaa = outlineSafeAngle % 120


            val bitmapComposeShader = 0
            // draw picture painter
            val paint = Paint()
            paint.textAlign = Paint.Align.CENTER
            paint.textSize = 64f
            paint.color = Color.Gray.toArgb()
            drawIntoCanvas {
                it.nativeCanvas.drawText(
                    "member : Premium",
                    center.x,
                    center.y,
                    paint
                )
            }

            //for color arc
            drawArc(
                circleOutlineColor,
                startAngle = angle3MidPoint + smallAngleOffset,
                sweepAngle = 120f - 2 * smallAngleOffset,
                style = Stroke(width = strokeWidth),
                useCenter = false
            )

            // for image arc
            drawImageArc(
                startAngle = angle1MidPoint + smallAngleOffset,
                sweepAngle = 120f - 2 * smallAngleOffset,
                style = Stroke(width = strokeWidth)
            )
            drawImageArc(
                startAngle = angle2MidPoint + smallAngleOffset,
                sweepAngle = 120f - 2 * smallAngleOffset,
                style = Stroke(width = strokeWidth)
            )
            drawImageArc(
                startAngle = angle3MidPoint + smallAngleOffset,
                sweepAngle = 120f - 2 * smallAngleOffset,
                style = Stroke(width = strokeWidth)
            )

            drawImage(imageBitmap, blendMode = BlendMode.DstAtop)

        }
    )
}

private fun DrawScope.drawImageArc(
    startAngle: Float,
    sweepAngle: Float,
    style: Stroke
) {
    drawArc(
        color = Color.Green,
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = style,
        blendMode = BlendMode.SrcOut
    )
}

private fun getSmallCircleRadius(componentWidth: Float): Float {
    return componentWidth / 2 * 0.76.toFloat()
}

@Preview(showBackground = true)
@Composable
fun BoardwayPieChartPreview() {
    JetpackComposeUiDemoTheme {
        BoardwayPieChart()
    }
}