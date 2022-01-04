package com.ericho.compose.demo.ui.touch

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.awaitDragOrCancellation
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.ericho.compose.demo.ImageUrl
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import kotlin.math.roundToInt

@Composable
fun BaseDragGestureDemo() {
    var boxSize = 100.dp
    var offset by remember { mutableStateOf(Offset.Zero) }
    val painter =
        rememberImagePainter(data = ImageUrl.morty)
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(Modifier
            .size(boxSize)
            .offset {
                IntOffset(offset.x.roundToInt(), offset.y.roundToInt())
            }
            .pointerInput(Unit) {
                forEachGesture {
                    awaitPointerEventScope {
                        var downPointer = awaitFirstDown()
                        while (true) {
                            var event = awaitDragOrCancellation(downPointer.id)
                            if (event == null) {
                                break
                            }
                            offset += event.positionChange()
                        }
                    }
                }
            }
        ) {
            Image(
                painter = painter, contentDescription = "Morty",
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(text = "BaseDragGestureDemo", modifier = Modifier.align(Alignment.TopCenter))
    }
}

@Preview("BaseDragGestureDemo")
@Composable
fun BaseDragGestureDemoPreview() {
    BaseDragGestureDemo()
}