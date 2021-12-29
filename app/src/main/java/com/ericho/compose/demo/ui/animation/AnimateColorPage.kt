package com.ericho.compose.demo.ui.animation

import androidx.compose.animation.Animatable
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*

@Composable
fun AnimateColorPage() {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(text = "Animate to some color (initial value = current value)")
        var color1 by remember { mutableStateOf(Color.Blue) }
        val intermediateColor1 by animateColorAsState(
            targetValue = color1,
            animationSpec = tween(3000)
        )
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Surface(
                color = intermediateColor1, shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.TopCenter)
            ) {

            }
        }
        Row() {
            Button(onClick = { color1 = Color.Red }) {
                Text(text = "Red")
            }
            Button(onClick = { color1 = Color.Blue }) {
                Text(text = "Blue")
            }
            Button(onClick = { color1 = Color.Green }) {
                Text(text = "Green")
            }
            Button(onClick = { color1 = Color.Magenta }) {
                Text(text = "Magenta")
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        //Part 2
        Text(text = "Animate to some color (initial value = current value)")
        var color2Int by remember {
            mutableStateOf(0)
        }
        val intermediaColor2 = remember(color2Int) { Animatable(Color.White) }
        LaunchedEffect(color2Int) {
            val targetColor = when (color2Int) {
                0 -> Color.Red
                1 -> Color.Blue
                2 -> Color.Green
                3 -> Color.Magenta
                else -> Color.Yellow
            }
            intermediaColor2.animateTo(targetColor, tween(3000))
        }
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Surface(
                color = intermediaColor2.value, shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.TopCenter)
            ) {}
        }
        Row() {
            Button(onClick = { color2Int = 0 }) {
                Text(text = "Red")
            }
            Button(onClick = { color2Int = 1 }) {
                Text(text = "Blue")
            }
            Button(onClick = { color2Int = 2 }) {
                Text(text = "Green")
            }
            Button(onClick = { color2Int = 3 }) {
                Text(text = "Magenta")
            }
            Button(onClick = { color2Int = 99 }) {
                Text(text = "Other")
            }
        }
    }
}

@Preview("AnimateColorPage")
@Composable
fun AnimateColorPagePreview() {
    AnimateColorPage()
}