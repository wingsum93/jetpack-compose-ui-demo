package com.ericho.compose.demo.ui.custom

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.ericho.compose.demo.R
import com.ericho.compose.demo.ui.theme.JetpackComposeUiDemoTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

@Composable
fun WaveLoadingDemo(
    modifier: Modifier = Modifier
) {
    var _progress by remember { mutableStateOf(0.5f) }
    var _velocity by remember { mutableStateOf(1.0f) }
    var _amplitude by remember { mutableStateOf(0.2f) }
    var _backImage by remember { mutableStateOf(false) }

    Column {
        WaveLoading(
            modifier = Modifier.weight(1f),
            backDrawType = if (_backImage) DrawType.DrawImage else DrawType.None,
            progress = _progress,
            velocity = _velocity,
            amplitude = _amplitude,

            ) {

            Row {
                Image(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.ic_rick_and_morty_1),
                    contentDescription = ""
                )

                Image(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.ic_rick_and_morty_1),
                    contentDescription = ""
                )

                Image(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.cat_5),
                    contentDescription = ""
                )
            }


        }
    }
}

@Preview(showBackground = false)
@Composable
fun WaveLoadingDemoPreview() {
    JetpackComposeUiDemoTheme {
        WaveLoadingDemo()
    }
}

private const val defaultAmlitude = 0.2f
private const val defaultVelocity = 1.0f
private const val waveDuration = 2000
private const val foreDrawAlpha = 0.5f
private const val scaleX = 1f
private const val scaleY = 1f

private val alphaBitmap by lazy {
    Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8)
}

