package com.ericho.compose.demo.ui.custom

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.drawable.toDrawable
import com.ericho.compose.demo.R
import androidx.compose.foundation.layout.size

@Composable
fun BoardwayTraditionPieChart(
    backgroundProgress: Float = 0.9f,
    progress: Float = 0.3f
) {
    var flowInProgress by remember { mutableStateOf(0f) }
    val outlineColor = Color(186, 223, 220, 255)
    val imageBitmap: ImageBitmap = ImageBitmap.imageResource(id = R.drawable.boardway_shape)
    val resource = LocalContext.current.resources
    AndroidView(modifier =
    Modifier
        .size(300.dp, 300.dp),
        factory = {
            DonutView(it)
                .also { donutView ->
                    donutView.setProgress(0f)
                    donutView.setDonutSection(3)
                    donutView.setDonutBackground(outlineColor.toArgb())
                    donutView.setDonutSectionSpaceAngle(5f)
                    donutView.setDonutForeground(
                        imageBitmap.asAndroidBitmap().toDrawable(
                            resource
                        )
                    )
                }
        },
        update = {
            it.setProgress(progress)
            it.currentBackgroundAnimationProgress = backgroundProgress
            it.invalidate()
        }
    )

}

@Preview
@Composable
fun BoardwayTraditionPieChartPreview_1() {
    BoardwayTraditionPieChart(
        backgroundProgress = .7f,
        progress = .2f
    )
}

@Preview
@Composable
fun BoardwayTraditionPieChartPreview_2() {
    BoardwayTraditionPieChart(
        backgroundProgress = 1f,
        progress = .2f
    )
}