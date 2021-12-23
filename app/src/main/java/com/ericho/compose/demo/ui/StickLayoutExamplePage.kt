package com.ericho.compose.demo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ericho.compose.demo.base.StandardButton

@Composable
fun StickLayoutExamplePage() {

    Column(modifier = Modifier.fillMaxWidth()) {
        StandardButton(text = "Text Next Page!")
    }

}