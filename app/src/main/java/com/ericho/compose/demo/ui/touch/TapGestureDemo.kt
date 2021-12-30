package com.ericho.compose.demo.ui.touch

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import timber.log.Timber
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size

@Preview
@Composable
fun TapGestureDemo() {
    val boxSize = 100.dp
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            Modifier
                .size(boxSize)
                .background(Color.Green)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = {
                            // double-click
                            Timber.d("Double tap")
                        },
                        onLongPress = {
                            // Long press
                            Timber.d("Long Press")
                        },
                        onPress = {
                            // Press
                            Timber.d("Press")
                        },
                        onTap = {
                            // Tap
                            Timber.d("1 tap")
                        }
                    )
                }
        )
    }
}