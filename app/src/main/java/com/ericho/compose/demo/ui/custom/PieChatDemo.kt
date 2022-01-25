package com.ericho.compose.demo.ui.custom

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

@Composable
fun PieChatDemo() {
    val backgroundColor = Color(0x6FD1CDCD)
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(10.dp)
    ) {
        val (pieChart, btn, txv1, txv2) = createRefs()
        var flowInProgress by remember { mutableStateOf(false) }
        val transition = updateTransition(targetState = flowInProgress, "trans")
        val outlineAngle by
        transition.animateFloat(label = "pAngle", transitionSpec = {
            if (targetState) {
                keyframes {
                    durationMillis = 5000
                    120f at 1000
                    240f at 3000
                    360f at 4000
                    330f at 4500
                }
            } else {
                keyframes {
                    durationMillis = 5000
                    300f at 1000
                    240f at 3000
                    120f at 5000
                }
            }
        }) {
            if (it) 360f else 0f
        }
        val pictureAngle by
        transition.animateFloat(label = "pAngle", transitionSpec = { tween(3000, 5000) }) {
            if (it) 360f else 0f
        }
        val outlineColor = Color(186, 223, 220, 255)
        BoardwayPieChart(
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
            outlineProgressAngle = outlineAngle,
            imageAngle = pictureAngle,
            strokeWidthDp = 200.dp,
            circleOutlineColor = outlineColor,
            separatorAngle = 3f
        )

        Button(
            onClick = { flowInProgress = !flowInProgress },
            modifier = Modifier.constrainAs(btn) {
                top.linkTo(pieChart.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
            Text(text = "Click to toggle on / off")
        }
        Text(text = "outlineAngle> $outlineAngle", Modifier.constrainAs(txv1) {
            bottom.linkTo(btn.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
        Text(text = "pictureAngle> $pictureAngle", Modifier.constrainAs(txv2) {
            bottom.linkTo(btn.top, 40.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
}

@Preview
@Composable
fun PieChatDemoPreview() {
    PieChatDemo()
}