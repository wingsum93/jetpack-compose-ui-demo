package com.ericho.compose.demo.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ericho.compose.demo.ColorUtil
import androidx.compose.foundation.layout.Row

@Composable
fun StandardIconButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val borderColor = remember(text) {
        ColorUtil.getRandomColor()
    }

    Button(
        onClick = onClick,
        modifier = modifier
            .border(2.dp, borderColor, RoundedCornerShape(5.dp))
    ) {
        Row(
//            modifier = modifier.border(2.dp, borderColor, RoundedCornerShape(5.dp))
        ) {
            Text(text = text)
            val vp = rememberVectorPainter(image = Icons.Default.KeyboardArrowRight)
            Image(painter = vp, contentDescription = null)
        }
    }
}


@Composable
@Preview
fun StandardIconButtonPreview_short() {
    StandardIconButton(text = "Push me")
}

@Composable
@Preview
fun StandardIconButtonPreview_long() {
    StandardIconButton(text = "BaseDragGestureDemo (detect drag in parent box view)")
}