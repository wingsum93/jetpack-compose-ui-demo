package com.ericho.compose.demo.ui.animation

import androidx.compose.animation.Animatable
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateIntSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
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
        Spacer(modifier = Modifier.height(40.dp))
        //Part 3
        Text(text = "Animate to some color (updateTransition)")
        var colorState: BoxState by remember {
            mutableStateOf(BoxState.First)
        }
        val colorTransition = updateTransition(targetState = colorState, label = "transition")
        val intermediateColor3 by colorTransition.animateColor(
            transitionSpec = { tween(3000) },
            label = "animateColor"
        ) {
            when (it) {
                BoxState.First -> Color.Blue
                BoxState.Second -> Color.Magenta
                BoxState.Third -> Color.Yellow
                BoxState.Fourth -> Color.Red
                BoxState.Fifth -> Color.Green
            }
        }
        val cornerRadius by colorTransition.animateDp(
            transitionSpec = { tween(3000) },
            label = "cornerRadius"
        ) {
            when (it) {
                BoxState.First -> 5.dp
                BoxState.Second -> 10.dp
                BoxState.Third -> 15.dp
                BoxState.Fourth -> 20.dp
                BoxState.Fifth -> 25.dp
            }
        }
        val colorSurfaceSize by colorTransition.animateIntSize(
            transitionSpec = { tween(3000) },
            label = "colorSurfaceSize"
        ) {
            when (it) {
                BoxState.First -> IntSize(60, 60)
                BoxState.Second -> IntSize(70, 70)
                BoxState.Third -> IntSize(80, 80)
                BoxState.Fourth -> IntSize(60, 100)
                BoxState.Fifth -> IntSize(100, 60)
            }
        }
//        val colorOffset by colorTransition.animateOffset(transitionSpec = { tween(3000)},
//            label = "colorSurfaceSize"
//        ) {
//            when(it){
//                BoxState.First-> Offset.Infinite
//                BoxState.Second-> Offset(20f,20)
//                BoxState.Third-> Offset.Zero
//                BoxState.Fourth-> IntSize(60,100)
//                BoxState.Fifth-> IntSize(100,60)
//            }
//        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        ) {
            Surface(
                color = intermediateColor3, shape = RoundedCornerShape(cornerRadius),
                modifier = Modifier
                    .size(colorSurfaceSize.width.dp, colorSurfaceSize.height.dp)
                    .align(Alignment.Center)
            ) {

            }
        }
        Row() {
            Button(onClick = { colorState = BoxState.First }) {
                Text(text = "First")
            }
            Button(onClick = { colorState = BoxState.Second }) {
                Text(text = "Second")
            }
            Button(onClick = { colorState = BoxState.Third }) {
                Text(text = "Third")
            }
            Button(onClick = { colorState = BoxState.Fourth }) {
                Text(text = "Fourth")
            }
            Button(onClick = { colorState = BoxState.Fifth }) {
                Text(text = "fifth")
            }
        }
    }
}

@Preview("AnimateColorPage")
@Composable
fun AnimateColorPagePreview() {
    AnimateColorPage()
}

enum class BoxState {
    First,
    Second,
    Third,
    Fourth,
    Fifth
}