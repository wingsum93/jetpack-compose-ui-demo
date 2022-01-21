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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

@Composable
fun PieChatDemo() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
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
                    360f at 5000
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

        BoardwayPieChart(
            Modifier
                .fillMaxWidth(.9f)
                .fillMaxHeight(.5f)
                .constrainAs(pieChart) {
                    centerTo(parent)
                },
            outlineProgressAngle = outlineAngle,
            imageAngle = pictureAngle
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