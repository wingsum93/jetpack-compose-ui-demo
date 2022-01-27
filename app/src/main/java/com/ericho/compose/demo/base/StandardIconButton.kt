package com.ericho.compose.demo.base

import androidx.compose.foundation.Image
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import com.ericho.compose.demo.ColorUtil
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth

@Composable
fun StandardIconButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val arrowColor = rememberSaveable(text, saver = ColorSaver()) {
        ColorUtil.getRandomColor()
    }
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        elevation = ButtonDefaults.elevation()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = text, modifier = Modifier.fillMaxWidth(0.8f))
            val vp = rememberVectorPainter(image = Icons.Default.KeyboardArrowRight)
            Image(
                painter = vp,
                contentDescription = null,
                colorFilter = ColorFilter.tint(arrowColor)
            )
        }
    }
}

class ColorSaver : Saver<Color, List<Float>> {
    override fun restore(value: List<Float>): Color? {
        return Color(value[0], value[1], value[2], value[3])
    }

    override fun SaverScope.save(value: Color): List<Float>? {
        return listOf(value.red, value.green, value.blue, value.alpha)
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