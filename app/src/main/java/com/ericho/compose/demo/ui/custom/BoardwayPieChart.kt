package com.ericho.compose.demo.ui.custom

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.ericho.compose.demo.R
import androidx.compose.foundation.layout.sizeIn

@Composable
fun BoardwayPieChart(
    modifier: Modifier = Modifier
) {
    val greenLight700 = Color(108, 243, 114, 206)
    val green700 = Color(13, 245, 23, 248)
    val red700 = Color(245, 13, 13, 248)
    val dark = Color(7, 7, 7, 248)
//    val p = painterResource(R.drawable.boardway_shape)
    val bitmap = ImageBitmap.imageResource(
        res = LocalContext.current.resources,
        id = R.drawable.boardway_shape
    )
    Canvas(
        modifier = modifier.sizeIn(
            minWidth = 300.dp,
            minHeight = 300.dp,
            maxHeight = 500.dp,
            maxWidth = 500.dp
        ),
        onDraw = {
            val heightInt = size.height.toInt()
            val widthInt = size.width.toInt()
            drawImage(image = bitmap, srcSize = IntSize(widthInt, heightInt))
            // Head
            drawCircle(
                Brush.linearGradient(
                    colors = listOf(greenLight700, green700)
                ),
                radius = size.width / 2,
                center = center,
                style = Stroke(width = size.width * 0.075f)
            )

            // Smile
            val smilePadding = size.width * 0.15f
            drawArc(
                color = red700,
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(smilePadding, smilePadding),
                size = Size(size.width - (smilePadding * 2f), size.height - (smilePadding * 2f))
            )

            // Left eye
            drawRect(
                color = dark,
                topLeft = Offset(size.width * 0.25f, size.height / 4),
                size = Size(smilePadding, smilePadding)
            )

            // Right eye
            drawRect(
                color = dark,
                topLeft = Offset((size.width * 0.75f) - smilePadding, size.height / 4),
                size = Size(smilePadding, smilePadding)
            )
        }
    )
}


@Preview(showBackground = true)
@Composable
fun BoardwayPieChartPreview() {
    BoardwayPieChart()
}