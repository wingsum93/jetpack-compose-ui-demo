package com.ericho.compose.demo.base

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateIntSize
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.ericho.compose.demo.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn


@ExperimentalCoroutinesApi
@Composable
fun AnimatedHeartButton(
    modifier: Modifier = Modifier,
    buttonState: Boolean = false,
    onToggle: () -> Unit = {},
) {

//    val toState by remember(buttonState) {
//        derivedStateOf {
//            if (buttonState.value) {
//                ACTIVE
//            } else {
//                IDLE
//            }
//        }
//    }

    val state = likeTransition(
        targetState = buttonState
    )

    HeartButton(
        modifier = modifier,
        enable = buttonState,
        state = state,
        onToggle = onToggle
    )
}

@ExperimentalCoroutinesApi
@Composable
private fun HeartButton(
    modifier: Modifier,
    enable: Boolean,
    state: LikeTransition,
    onToggle: () -> Unit,
) {
    val painter =
        rememberImagePainter(R.drawable.heart_red)
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .widthIn(max = 100.dp)
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            colorFilter = ColorFilter.tint(state.color),
            modifier = Modifier
                .size(state.size.width.dp, state.size.height.dp)
                .clickable(
                    onClick = onToggle,
                )

        )
    }

}


object HeartAnimationDefinition {
    enum class HeartButtonState {
        IDLE, ACTIVE
    }
}

class LikeTransition(
    color: State<Color>,
    size: State<IntSize>
) {
    val color by color
    val size by size
}

@Composable
private fun likeTransition(targetState: Boolean): LikeTransition {
    val transition = updateTransition(
        targetState = targetState,
        label = "updateTransition"
    )

    val iconColor = transition.animateColor(label = "iconColor",
        transitionSpec = {
            if (this.targetState) {
                keyframes {
                    durationMillis = 500
//                    Color.Gray at 0
                    Color(0xD6, 0x44, 0xF7, 0xFF) at 100
                    Color(0xF5, 0x58, 0xF, 0xFF) at 200
                    Color(0x8B, 0xC3, 0x4A, 0xFF) at 400
//                    Color.Red at 500
                }
            } else {
                keyframes {
                    durationMillis = 500
//                    Color.Red at 0
                    Color.Black at 50
                    Color(0x6, 0xA, 0xF0, 0xFF) at 100
                    Color(0xC7, 0x9C, 0x1B, 0xFF) at 200
                    Color(0x39, 0x68, 0x2, 0xFF) at 400
//                    Color.Gray at 500
                }
            }
        }) { state ->
        if (state) {
            Color.Red
        } else
            Color.Gray
    }

    val intSize = transition.animateIntSize(label = "IntSize", transitionSpec = {
        if (targetState) {
            keyframes {
                durationMillis = 500
                expandedIconSize at 100
                idleIconSize at 200
            }
        } else {
            keyframes {
                durationMillis = 500
                expandedIconSize at 100
                idleIconSize at 200
            }
        }
    }) { _ ->
        idleIconSize
    }
    return remember(transition) {
        LikeTransition(iconColor, intSize)
    }
}

private val idleIconSize = IntSize(20, 20)
private val expandedIconSize = IntSize(50, 50)

@ExperimentalCoroutinesApi
@Preview
@Composable
fun AnimateHeartButtonPreview() {
    AnimatedHeartButton(buttonState = false)
}











