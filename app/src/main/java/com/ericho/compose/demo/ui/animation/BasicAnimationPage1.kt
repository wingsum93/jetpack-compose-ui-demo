package com.ericho.compose.demo.ui

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ericho.compose.demo.R
import kotlinx.coroutines.launch

@Composable
fun BasicAnimationPage1() {
    var showImage by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    ConstraintLayout(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        val (image, b, text) = createRefs()
        val painter = painterResource(id = R.drawable.ic_rick_and_morty_1)
        AnimatedVisibility(
            visible = showImage,
            enter = fadeIn() + expandIn(
                expandFrom = Alignment.Center,
                initialSize = { IntSize(50, 50) }),
            exit = fadeOut() + shrinkOut(
                shrinkTowards = Alignment.Center,
                targetSize = { IntSize(50, 50) }),
            modifier = Modifier.constrainAs(image) {
                centerHorizontallyTo(b)
                bottom.linkTo(b.top, 10.dp)
            }
        ) {
            Image(
                painter = painter,
                contentDescription = "",
                modifier = Modifier
            )
        }
        Button(
            onClick = {
                scope.launch {
                    showImage = !showImage
                }
            }, modifier = Modifier.constrainAs(b) {
                centerVerticallyTo(parent)
                centerHorizontallyTo(parent)
            }
        ) {
            Text(text = "Click to switch !")
        }
        Text(
            text = "AnimatedVisibility: fadeIn + expandIn, fadeOut + shrinkOut",
            modifier = Modifier.constrainAs(text) {
                top.linkTo(parent.top)
                centerHorizontallyTo(parent)
            }
        )
    }
}

@Preview
@Composable
fun PreviewBasicAnimationPage1() {
    BasicAnimationPage1()
}