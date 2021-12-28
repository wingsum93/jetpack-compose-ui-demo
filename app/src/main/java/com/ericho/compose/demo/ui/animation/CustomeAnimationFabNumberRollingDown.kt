package com.ericho.compose.demo.ui.animation

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ericho.compose.demo.R
import com.ericho.compose.demo.base.FloatingActionButton

@Composable
fun CustomAnimationFabNumberRollingDown() {
    Scaffold(floatingActionButton = {
        val arrowColor = Color(97, 97, 97, 255)
        val numberBackgroundColor = Color(168, 168, 168, 255)
        val painter = painterResource(id = R.drawable.ic_baseline_arrow_downward_24)
        FloatingActionButton(
            backgroundColor = Color.White,
            onClick = { /*TODO*/ }) {
            Icon(painter = painter, contentDescription = "", tint = arrowColor)
            Text(
                text = "1211", fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier
                    .background(
                        numberBackgroundColor,
                        RoundedCornerShape(percent = 40)
                    )
                    .align(Alignment.TopCenter)
            )
        }

    }) {

    }
}

@Preview
@Composable
fun PreviewCustomAnimationFabNumberRollingDown() {
    CustomAnimationFabNumberRollingDown()
}