package com.ericho.compose.demo.ui.animation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
    var num by remember { mutableStateOf(1999) }
    Scaffold(floatingActionButton = {
        val arrowColor = Color(97, 97, 97, 255)
        val numberBackgroundColor = Color(168, 168, 168, 255)
        val painter = painterResource(id = R.drawable.ic_baseline_arrow_downward_24)
        FloatingActionButton(
            backgroundColor = Color.White,
            onClick = { /*TODO*/ }) {
            Icon(painter = painter, contentDescription = "", tint = arrowColor)
            Text(
                text = num.toString(), fontSize = 14.sp,
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
        Column(
            modifier = Modifier.padding(it)
        ) {
            Button(onClick = { num = 2000 }) {
                Text(text = "2000")
            }

            Button(onClick = { num = 1891 }) {
                Text(text = "1891")
            }

            Button(onClick = { num = 1588 }) {
                Text(text = "1588")
            }

            Button(onClick = { num = 1463 }) {
                Text(text = "1463")
            }

            Button(onClick = { num = 1321 }) {
                Text(text = "1321")
            }

            Button(onClick = { num = 999 }) {
                Text(text = "999")
            }
        }
    }
}

@Preview
@Composable
fun PreviewCustomAnimationFabNumberRollingDown() {
    CustomAnimationFabNumberRollingDown()
}