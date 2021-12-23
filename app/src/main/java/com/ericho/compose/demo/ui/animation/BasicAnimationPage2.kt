package com.ericho.compose.demo.ui.animation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BasicAnimationPage2() {


    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Box(modifier = Modifier) {
//            AnimationConstants()
        }
    }
}