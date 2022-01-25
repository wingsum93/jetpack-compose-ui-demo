package com.ericho.compose.demo.ui.custom

import android.graphics.Paint
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ericho.compose.demo.R
import com.ericho.compose.demo.ui.theme.JetpackComposeUiDemoTheme
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.sizeIn

@Composable
fun BoardwayPieChart(
    modifier: Modifier = Modifier,
    // 0..360
    outlineProgressAngle: Float = 320f,
    // 0..360
    imageAngle: Float = 220f,
    strokeWidthDp: Dp = 150.dp,
    section: Int = 3,
    separatorAngle: Float = 20f,
    circleOutlineColor: Color = Color(65, 236, 182, 255)
) {
    val outlineSafeAngle =
        remember(outlineProgressAngle) { outlineProgressAngle.coerceIn(0f..360f) }
    val imageSafeAngle = remember(imageAngle) { imageAngle.coerceIn(0f..360f) }
    val separatorSafeAngle = remember(separatorAngle) { separatorAngle.coerceIn(1f..60f) }
    //calculate sector of 3 arc
    val imageBitmap: ImageBitmap = ImageBitmap.imageResource(id = R.drawable.boardway_shape)
    val strokeWidth = strokeWidthDp.value
    Canvas(
        modifier = modifier
            .aspectRatio(1f)
            .sizeIn(
                minWidth = 100.dp,
                minHeight = 100.dp,
                maxHeight = 400.dp,
                maxWidth = 400.dp
            )
            .clip(CircleShape),
        onDraw = {
            //data part
            val angleOffset = -90f
            val angle1MidPoint = 0 + angleOffset
            val smallAngleOffset = separatorSafeAngle / 2
            val sectionFullAngle = 360 / section
            val sectionReducedAngle = sectionFullAngle - separatorSafeAngle
            // draw picture painter
            val paint = Paint()
            paint.textAlign = Paint.Align.CENTER
            paint.textSize = 64f
            paint.color = Color(0x94837878).toArgb()
            drawIntoCanvas {
                it.nativeCanvas.drawText(
                    "member : Premium",
                    center.x,
                    center.y,
                    paint
                )
            }
            for (i in 0 until section) {
                var outLineAngleTemp = outlineSafeAngle - sectionFullAngle * i
                var imageAngleTemp = imageSafeAngle - sectionFullAngle * i
                outLineAngleTemp -= smallAngleOffset
                imageAngleTemp -= smallAngleOffset
                val outLineIsInThisSector = outLineAngleTemp > 0
                val imageIsInThisSector = imageAngleTemp > 0
                val outlineSweepAngle = outLineAngleTemp.coerceIn(0f, sectionReducedAngle)
                val imageSweepAngle = imageAngleTemp.coerceIn(0f, sectionReducedAngle)
                if (outLineIsInThisSector) {
                    //draw color arc
                    drawArc(
                        circleOutlineColor,
                        startAngle = i * sectionFullAngle + angle1MidPoint + smallAngleOffset,
                        sweepAngle = outlineSweepAngle,
                        style = Stroke(width = strokeWidth),
                        useCenter = false,
                        blendMode = BlendMode.SrcIn
                    )
                }
                if (imageIsInThisSector) {
                    //draw image arc
                    drawImageArc(
                        startAngle = i * sectionFullAngle + angle1MidPoint + smallAngleOffset,
                        sweepAngle = imageSweepAngle,
                        style = Stroke(width = strokeWidth),
                        color = circleOutlineColor
                    )
                }
            }

            drawImage(imageBitmap, blendMode = BlendMode.DstAtop)
        }
    )
}

private fun DrawScope.drawImageArc(
    startAngle: Float,
    sweepAngle: Float,
    style: Stroke,
    color: Color = Color.Green
) {
    drawArc(
        color = color,
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = style,
        blendMode = BlendMode.SrcOut
    )
}

@Preview(showBackground = true)
@Composable
fun BoardwayPieChartPreview() {
    JetpackComposeUiDemoTheme {
        BoardwayPieChart()
    }
}

@Preview(showBackground = true)
@Composable
fun BoardwayPieChartPreview_340_10() {
    JetpackComposeUiDemoTheme {
        BoardwayPieChart(
            outlineProgressAngle = 340f,
            imageAngle = 10f
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BoardwayPieChartPreview_340_15() {
    JetpackComposeUiDemoTheme {
        BoardwayPieChart(
            outlineProgressAngle = 340f,
            imageAngle = 15f
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BoardwayPieChartPreview_340_100() {
    JetpackComposeUiDemoTheme {
        BoardwayPieChart(
            outlineProgressAngle = 340f,
            imageAngle = 100f
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BoardwayPieChartPreview_340_110() {
    JetpackComposeUiDemoTheme {
        BoardwayPieChart(
            outlineProgressAngle = 340f,
            imageAngle = 110f
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BoardwayPieChartPreview_340_120() {
    JetpackComposeUiDemoTheme {
        BoardwayPieChart(
            outlineProgressAngle = 340f,
            imageAngle = 120f
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BoardwayPieChartPreview_340_240() {
    JetpackComposeUiDemoTheme {
        BoardwayPieChart(
            outlineProgressAngle = 340f,
            imageAngle = 240f
        )
    }
}