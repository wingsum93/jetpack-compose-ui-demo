package com.ericho.compose.demo.ui.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.ericho.compose.demo.R

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun BasicAnimationContentPage2() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        Text(text = "Picture")
        Spacer(modifier = Modifier.height(30.dp))
        var expanded by remember { mutableStateOf(false) }
        Surface(
            color = MaterialTheme.colors.primary,
            onClick = { expanded = !expanded }
        ) {
            AnimatedContent(
                targetState = expanded,
                transitionSpec = {
                    fadeIn(animationSpec = tween(150, 150)) with
                            fadeOut(animationSpec = tween(150)) using
                            SizeTransform { initialSize, targetSize ->
                                if (targetState) {
                                    keyframes {
                                        // Expand horizontally first.
                                        IntSize(targetSize.width, initialSize.height) at 150
                                        durationMillis = 300
                                    }
                                } else {
                                    keyframes {
                                        // Shrink vertically first.
                                        IntSize(initialSize.width, targetSize.height) at 150
                                        durationMillis = 300
                                    }
                                }
                            }
                }
            ) { targetExpanded ->
                val painter = painterResource(id = R.drawable.ic_baseline_headphones_24)
                if (targetExpanded) {
                    Text(
                        text = "Repeatable gives you an option to create repeatable animations. Important! All animations I showed before are not repeatable — I pressed the “Ride” button to make the animation run.\n" +
                                "In general repeatable is a wrapper on your actual animation. So here is a simple example:",
                        modifier = Modifier.background(Color.Gray)
                    )
                } else {
                    Icon(
                        painter = painter, contentDescription = "",
                        modifier = Modifier
                            .background(Color.Gray)
                            .size(100.dp)
                    )
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun PreviewBasicAnimationPage3() {
    BasicAnimationContentPage2()
}