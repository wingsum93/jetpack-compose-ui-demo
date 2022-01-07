package com.ericho.compose.demo.ui.animation

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ericho.compose.demo.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size

@Composable
fun AnimateGraphicLayerDemo() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        val animationSpec = infiniteRepeatable<Float>(
            animation = tween(
                durationMillis = 2500,
                easing = LinearEasing,
            )
        )
        val xRotation by animateValues(
            values = listOf(0f, 180f, 180f, 0f, 0f),
            animationSpec = animationSpec
        )
        val yRotation by animateValues(
            values = listOf(0f, 0f, 180f, 180f, 0f),
            animationSpec = animationSpec
        )

        Text(text = "AnimateGraphicLayerDemo", modifier = Modifier.align(Alignment.TopCenter))
        val painter = painterResource(id = R.drawable.ic_rick_and_morty_1)
        Image(painter = painter, contentDescription = "morty", modifier = Modifier
            .size(120.dp)
            .graphicsLayer {
                rotationX = xRotation
                rotationY = yRotation
            })
    }
}

@Composable
fun animateValues(
    values: List<Float>,
    animationSpec: AnimationSpec<Float> = spring(),
): State<Float> {
    // 1. Create the groups zipping with next entry
    val groups by rememberUpdatedState(newValue = values.zipWithNext())

    // 2. Start the state with the first value
    val state = remember { mutableStateOf(values.first()) }

    LaunchedEffect(key1 = groups) {
        val (_, setValue) = state

        // Start the animation from 0 to groups quantity
        animate(
            initialValue = 0f,
            targetValue = groups.size.toFloat(),
            animationSpec = animationSpec,
        ) { frame, _ ->
            // Get which group is being evaluated
            val integerPart = frame.toInt()
            val (initialValue, finalValue) = groups[frame.toInt()]

            // Get the current "position" from the group animation
            val decimalPart = frame - integerPart

            // Calculate the progress between the initial and final value
            setValue(
                initialValue + (finalValue - initialValue) * decimalPart
            )
        }
    }
    return state
}

@Preview
@Composable
fun AnimateGraphicLayerDemoPreview() {
    AnimateGraphicLayerDemo()
}