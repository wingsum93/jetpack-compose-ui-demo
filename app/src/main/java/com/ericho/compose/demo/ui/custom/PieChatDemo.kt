package com.ericho.compose.demo.ui.custom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*

@Composable
fun PieChatDemo() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        BoardwayPieChart(
            Modifier
                .fillMaxWidth(.9f)
                .fillMaxHeight(.5f)
        )
    }
}

@Preview
@Composable
fun PieChatDemoPreview() {
    PieChatDemo()
}