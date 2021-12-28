package com.ericho.compose.demo.ui.animation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AnimationCrossFadePage() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        var currentPage by remember { mutableStateOf("A") }
        Crossfade(targetState = currentPage, animationSpec = tween(1000)) { screen ->
            when (screen) {
                "A" -> Text("Page A")
                "B" -> Text("Page B")
            }
        }
        Button(
            onClick = {
                currentPage = if (currentPage == "B") "A" else "B"
            }, modifier = Modifier
        ) {
            Text(text = "Click to switch !")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAnimationCrossFadePage() {
    AnimationCrossFadePage()
}
