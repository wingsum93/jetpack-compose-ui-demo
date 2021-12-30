package com.ericho.compose.demo.ui.touch

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import kotlin.math.roundToInt

@Composable
fun DragGestureDemo() {
    var boxSize = 100.dp
    var offset by remember { mutableStateOf(Offset.Zero) }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(Modifier
            .size(boxSize)
            .offset {
                IntOffset(offset.x.roundToInt(), offset.y.roundToInt())
            }
            .background(Color.Green)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        // Drag to start
                    },
                    onDragEnd = {
                        // Drag end
                        Log.d("1999", "drag end")
                    },
                    onDragCancel = {
                        // Drag to cancel
                        Log.d("1999", "drag cancel")
                    },
                    onDrag = { change: PointerInputChange, dragAmount: Offset ->
                        // Dragging
                        offset += dragAmount
                    }
                )
            }
        )
    }
}

@Preview
@Composable
fun DragGestureDemoPreview() {
    DragGestureDemo()
}