package com.ericho.compose.demo.base

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ericho.compose.demo.ColorUtil

@Composable
fun StandardButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val borderColor = remember(text) {
        ColorUtil.getRandomColor()
    }
    TextButton(
        onClick = onClick,
        modifier = modifier
            .border(2.dp, borderColor, RoundedCornerShape(20.dp))
    ) {
        Text(text = text)
    }
}


@Composable
@Preview
fun PreviewStandardButton() {
    StandardButton(text = "Push me")
}