package com.ericho.compose.demo.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ericho.compose.demo.base.StandardButton

@Composable
fun ConstraintLayoutExamplePage() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val (b1, b2, b3) = createRefs()
        StandardButton(text = "Right Top!", Modifier.constrainAs(b1) {
            top.linkTo(parent.top, 20.dp)
            end.linkTo(parent.end, 10.dp)
        })
        StandardButton(text = "Left Button", Modifier.constrainAs(b2) {
            bottom.linkTo(parent.bottom, 20.dp)
            start.linkTo(parent.start, 10.dp)
        })
        StandardButton(text = "3 Button", Modifier.constrainAs(b3) {
            bottom.linkTo(b2.top, 10.dp)
            start.linkTo(b2.end, 10.dp)
        })
    }
}