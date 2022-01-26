package com.ericho.compose.demo.ui.custom

import androidx.compose.foundation.background
import androidx.compose.material.Button
import androidx.compose.material.Text
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.graphics.drawable.toDrawable
import com.ericho.compose.demo.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

@Composable
fun PieChatTraditionalDemo() {
    val backgroundColor = Color(0x6FD1CDCD)
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(10.dp)
    ) {
        val (pieChart, btn1, btn2, txv1, txv2) = createRefs()
        var flowInProgress by remember { mutableStateOf(0f) }
        val outlineColor = Color(186, 223, 220, 255)
        val imageBitmap: ImageBitmap = ImageBitmap.imageResource(id = R.drawable.boardway_shape)
        val resource = LocalContext.current.resources
        AndroidView(modifier =
        Modifier
            .size(300.dp, 300.dp)
            .constrainAs(pieChart) {
                linkTo(
                    parent.start,
                    parent.top,
                    parent.end,
                    parent.bottom,
                    10.dp,
                    20.dp,
                    10.dp,
                    20.dp
                )
            },
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
                it.setProgress(flowInProgress)
                it.startAnimation()
            }
        )

        Button(
            onClick = { flowInProgress = .5f },
            modifier = Modifier.constrainAs(btn1) {
                top.linkTo(pieChart.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
            Text(text = "Click to 180º")
        }
        Button(
            onClick = { flowInProgress = .7f },
            modifier = Modifier.constrainAs(btn2) {
                top.linkTo(pieChart.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(btn1.end)
                end.linkTo(parent.end)
            }) {
            Text(text = "Click to 240º")
        }
        Text(text = "outlineAngle> ", Modifier.constrainAs(txv1) {
            bottom.linkTo(btn1.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
        Text(text = "target picture º> ${flowInProgress * 360}", Modifier.constrainAs(txv2) {
            bottom.linkTo(btn1.top, 40.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
}

@Preview
@Composable
fun PieChatTraditionalDemoPreview() {
    PieChatTraditionalDemo()
}