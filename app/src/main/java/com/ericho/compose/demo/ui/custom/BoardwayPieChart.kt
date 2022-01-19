package com.ericho.compose.demo.ui.custom

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.ericho.compose.demo.R
import com.ericho.compose.demo.ui.theme.JetpackComposeUiDemoTheme
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.sizeIn

@Composable
fun BoardwayPieChart(
    modifier: Modifier = Modifier,
    outlineProgressAngle: Float = 0f,
    imageAngle: Float = 0f,
    separatorAngle: Float = 5f,
    circleOutlineColor: Color = Color(65, 236, 182, 255)
) {

    val outlineSafeAngle =
        remember(outlineProgressAngle) { outlineProgressAngle.coerceIn(0f..360f) }
    val imageSafeAngle = remember(imageAngle) { imageAngle.coerceIn(0f..360f) }
    val separatorSafeAngle = remember(separatorAngle) { separatorAngle.coerceIn(3f..20f) }
    val bitmap = ImageBitmap.imageResource(
        res = LocalContext.current.resources,
        id = R.drawable.boardway_shape
    )
    //calculate sector of 3 arc
    val isOutlineFull = outlineSafeAngle < 360f
    val isImageFull = imageSafeAngle < 360f

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
            val heightInt = size.height.toInt()
            val widthInt = size.width.toInt()
            val spaceColor = Color.White
            val angleOffset = -90f
            val angle1 = 0 + angleOffset
            val angle2 = 120 + angleOffset
            val angle3 = 240 + angleOffset

            val stokeWidth = size.width * 0.24f
            val currentOutlineAngle = outlineSafeAngle - imageSafeAngle
            ///
            drawImage(image = bitmap, srcSize = IntSize(widthInt, heightInt))
            // Head
            drawCircle(
                Color.White,
                radius = getSmallCircleRadius(size.width),
                center = center,
                style = Fill
            )
            //basic outline arc
            drawArc(
                color = circleOutlineColor,
                startAngle = angle1 - currentOutlineAngle,
                sweepAngle = currentOutlineAngle,
                useCenter = false,
                style = Stroke(width = stokeWidth)
            )
            //3 x space arc
            drawArc(
                color = spaceColor,
                startAngle = angle1 - separatorSafeAngle / 2,
                sweepAngle = separatorSafeAngle,
                useCenter = false,
                style = Stroke(width = stokeWidth)
            )
            drawArc(
                color = spaceColor,
                startAngle = angle2 - separatorSafeAngle / 2,
                sweepAngle = separatorSafeAngle,
                useCenter = false,
                style = Stroke(width = stokeWidth)
            )
            drawArc(
                color = spaceColor,
                startAngle = angle3 - separatorSafeAngle / 2,
                sweepAngle = separatorSafeAngle,
                useCenter = false,
                style = Stroke(width = stokeWidth)
            )
        }
    )
}

private fun getSmallCircleRadius(componentWidth: Float): Float {
    return componentWidth / 2 * 0.76.toFloat()
}

@Preview(showBackground = false)
@Composable
fun BoardwayPieChartPreview() {
    JetpackComposeUiDemoTheme {
        BoardwayPieChart()
    }
}