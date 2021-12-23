package com.ericho.compose.demo

import androidx.compose.ui.graphics.Color

object ColorUtil {
    fun getRandomColor(): Color {
        val red = (255 * Math.random()).toInt()
        val green = (255 * Math.random()).toInt()
        val blue = (255 * Math.random()).toInt()
        val alpha = (128 * Math.random() + 128).toInt()
        return Color(red, green, blue, alpha)
    }
}