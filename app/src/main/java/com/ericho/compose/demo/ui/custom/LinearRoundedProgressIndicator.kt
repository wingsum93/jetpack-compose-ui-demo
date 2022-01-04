package com.ericho.compose.demo.ui.custom

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.focusable
import androidx.compose.foundation.progressSemantics
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size

@Composable
fun LinearRoundedProgressIndicator(
    /*@FloatRange(from = 0.0, to = 1.0)*/
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary,
    backgroundColor: Color = color.copy(alpha = ProgressIndicatorDefaults.IndicatorBackgroundOpacity)
) {
    val linearIndicatorHeight = ProgressIndicatorDefaults.StrokeWidth
    val linearIndicatorWidth = 240.dp
    Canvas(
        modifier
            .progressSemantics(progress)
            .size(linearIndicatorWidth, linearIndicatorHeight)
            .focusable()
    ) {
        val strokeWidth = size.height
        drawRoundedLinearIndicatorBackground(backgroundColor, strokeWidth)
        drawRoundedLinearIndicator(0f, progress, color, strokeWidth)
    }
}

private fun DrawScope.drawRoundedLinearIndicatorBackground(
    color: Color,
    strokeWidth: Float
) = drawRoundedLinearIndicator(0f, 1f, color, strokeWidth)

private fun DrawScope.drawRoundedLinearIndicator(
    startFraction: Float,
    endFraction: Float,
    color: Color,
    strokeWidth: Float
) {
    val width = size.width
    val height = size.height
    // Start drawing from the vertical center of the stroke
    val yOffset = height / 2

    val isLtr = layoutDirection == LayoutDirection.Ltr
    val barStart = (if (isLtr) startFraction else 1f - endFraction) * width
    val barEnd = (if (isLtr) endFraction else 1f - startFraction) * width

    // Progress line
    drawLine(
        color,
        Offset(barStart, yOffset),
        Offset(barEnd, yOffset),
        strokeWidth,
        StrokeCap.Round
    )
}

@Preview
@Composable
fun LinearRoundedProgressIndicatorPreview() {
    LinearRoundedProgressIndicator(.5f)
}