package com.ericho.compose.demo.ui.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun BasicAnimationContentPage() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(text = "slideInVertically + fadeIn, slideOutVertically + fadeOut")
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            var count by remember { mutableStateOf(0) }
            Button(onClick = {
                count++
            }) {
                Text("Add")
            }
            Button(onClick = {
                count--
            }) {
                Text("Remove")
            }
            AnimatedContent(
                targetState = count,
                modifier = Modifier,
                transitionSpec = {
                    // Compare the incoming number with the previous number.
                    if (targetState > initialState) {
                        // If the target number is larger, it slides up and fades in
                        // while the initial (smaller) number slides up and fades out.
                        slideInVertically { height -> height } + fadeIn() with
                                slideOutVertically { height -> -height } + fadeOut()
                    } else {
                        // If the target number is smaller, it slides down and fades in
                        // while the initial number slides down and fades out.
                        slideInVertically { height -> -height } + fadeIn() with
                                slideOutVertically { height -> height } + fadeOut()
                    }.using(
                        // Disable clipping since the faded slide-in/out should
                        // be displayed out of bounds.
                        SizeTransform(clip = false)
                    )
                }
            ) { targetCount ->
                Text(
                    text = "$targetCount",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .border(1.dp, Color.Blue, RoundedCornerShape(2.dp))
                        .padding(8.dp)
                )
            }
        }

        Text(text = "? + fadeIn, slideOutVertically + fadeOut")
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            var count by remember { mutableStateOf(0) }
            Button(onClick = {
                count++
            }) {
                Text("Add")
            }
            Button(onClick = {
                count--
            }) {
                Text("Remove")
            }
            Spacer(Modifier.width(100.dp))
            AnimatedContent(
                targetState = count,
                modifier = Modifier,
                transitionSpec = {
                    // Compare the incoming number with the previous number.
                    if (targetState > initialState) {
                        fadeIn(tween(300)) + slideInHorizontally(tween(300)) { height -> height } with
                                fadeOut(tween(300)) + slideOutHorizontally(tween(300)) { height -> -height }
                    } else {
                        fadeIn(tween(300)) + slideInHorizontally(tween(300)) { height -> -height } with
                                fadeOut(tween(300)) + slideOutHorizontally(tween(300)) { height -> height }
                    }.using(
                        // Disable clipping since the faded slide-in/out should
                        // be displayed out of bounds.
                        SizeTransform(clip = false)
                    )
                }
            ) { targetCount ->
                Text(
                    text = "$targetCount",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun PreviewBasicAnimationPage2() {
    BasicAnimationContentPage()
}