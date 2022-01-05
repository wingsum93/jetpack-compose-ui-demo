package com.ericho.compose.demo.ui.custom

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.ericho.compose.demo.ImageUrl
import timber.log.Timber
import androidx.compose.foundation.layout.*

@Composable
fun ClickEventOverlayDemo() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .size(300.dp)
                .background(Color.Green)
        ) {
            Image(
                painter = rememberImagePainter(data = ImageUrl.morty),
                contentDescription = null,
                Modifier.clickable {
                    Timber.d("morty icon clicked")
                })
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {
                Timber.d("first icon clicked")
            }) {
                Icon(Icons.Filled.ArrowBack, null)
            }

            IconButton(onClick = {
                Timber.d("second icon clicked")
            }) {
                Icon(Icons.Filled.PlayArrow, null)
            }

            IconButton(onClick = {
                Timber.d("third icon clicked")
            }) {
                Icon(Icons.Filled.ArrowForward, null)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview
@Composable
fun ClickEventOverlayDemoPreview() {
    ClickEventOverlayDemo()
}