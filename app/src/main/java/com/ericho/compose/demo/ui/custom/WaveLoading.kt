package com.ericho.compose.demo.ui.custom

import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Shader
import androidx.annotation.FloatRange
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.withSave
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.transform
import toColor
import toGrayscale
import androidx.compose.foundation.layout.*
import kotlin.math.roundToInt


private const val defaultAmlitude = 0.2f
private const val defaultVelocity = 1.0f
private const val waveDuration = 2000
private const val foreDrawAlpha = 0.5f
private const val scaleX = 1f
private const val scaleY = 1f

private val alphaBitmap by lazy {
    Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8)
}

data class WaveConfig(
    val foreDrawType: DrawType,
    val backDrawType: DrawType,
    @FloatRange(from = 0.0, to = 1.0) val progress: Float,
    @FloatRange(from = 0.0, to = 1.0) val amplitude: Float,
    @FloatRange(from = 0.0, to = 1.0) val velocity: Float
)

val LocalWave = compositionLocalOf<WaveConfig> {
    error("No Local WaveConfig")
}

@Composable
fun WaveLoading(
    modifier: Modifier = Modifier,
    foreDrawType: DrawType = DrawType.DrawImage,
    backDrawType: DrawType = rememberDrawColor(color = Color.LightGray),
    @FloatRange(from = 0.0, to = 1.0) progress: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) amplitude: Float = defaultAmlitude,
    @FloatRange(from = 0.0, to = 1.0) velocity: Float = defaultVelocity,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier.fillMaxSize()
    ) {
        var _size by remember { mutableStateOf(IntSize.Zero) }

        var _bitmap by remember {
            mutableStateOf(Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565))
        }
        AndroidView(
            modifier = Modifier.fillMaxSize(), // Occupy the max size in the Compose UI tree
            factory = { context ->
                // Creates custom view
                object : AbstractComposeView(context) {

                    @Composable
                    override fun Content() {
                        Box(
                            Modifier
                                .wrapContentSize()
                                .onSizeChanged {
                                    _size = it
                                }) {

                            content()
                        }
                    }

                    override fun dispatchDraw(canvas: Canvas?) {
                        if (width == 0 || height == 0) return
                        val source = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
                        val canvas2 = Canvas(source)
                        super.dispatchDraw(canvas2)
                        _bitmap = Bitmap.createBitmap(
                            source,
                            (source.width - _size.width) / 2,
                            (source.height - _size.height) / 2,
                            _size.width,
                            _size.height
                        )
                        source.recycle()
                    }

                }
            }

        )

        CompositionLocalProvider(
            LocalWave provides WaveConfig(foreDrawType, backDrawType, progress, amplitude, velocity)
        ) {
            with(LocalDensity.current) {
                Box(
                    Modifier
                        .width(_size.width.toDp())
                        .height(_size.height.toDp())
                        .align(Alignment.Center)
                        .clipToBounds()
                ) {
                    WaveLoadingInternal(bitmap = _bitmap)
                }
            }
        }
    }
}

@Composable
private fun WaveLoadingInternal(bitmap: Bitmap) {

    val dp = LocalDensity.current.run {
        1.dp.toPx() //??????dp?????????????????????????????????????????????????????????????????????dp?????????
    }

    val transition = rememberInfiniteTransition()

    val (foreDrawType, backDrawType, progress, amplitude, velocity) = LocalWave.current

    val forePaint = remember(foreDrawType, bitmap) {
        Paint().apply {
            alpha = foreDrawAlpha
            shader = BitmapShader(
                when (foreDrawType) {
                    is DrawType.DrawColor -> bitmap.toColor(foreDrawType.color)
                    is DrawType.DrawImage -> bitmap
                    else -> alphaBitmap
                },
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP
            )
        }
    }

    val backPaint = remember(backDrawType, bitmap) {
        Paint().apply {
            shader = BitmapShader(
                when (backDrawType) {
                    is DrawType.DrawColor -> bitmap.toColor(backDrawType.color)
                    is DrawType.DrawImage -> bitmap.toGrayscale()
                    else -> alphaBitmap
                },
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP
            )
        }
    }

    val waves = remember(Unit) {
        listOf(
            WaveAnim(waveDuration, 0f, 0f, scaleX, scaleY),
            WaveAnim((waveDuration * 0.75f).roundToInt(), 0f, 0f, scaleX, scaleY),
            WaveAnim((waveDuration * 0.5f).roundToInt(), 0f, 0f, scaleX, scaleY)
        )
    }

    val animates = waves.map { transition.animateOf(duration = it.duration) }


    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {

        drawIntoCanvas { canvas ->

            //draw back image
            canvas.drawRect(0f, 0f, size.width, size.height, backPaint)

            waves.forEachIndexed { index, wave ->

                canvas.withSave {

                    val maxWidth = 2 * scaleX * size.width / velocity.coerceAtLeast(0.1f)
                    val maxHeight = scaleY * size.height
                    val offsetX = maxWidth / 2 * (1 - animates[index].value) - wave.offsetX
                    val offsetY = wave.offsetY

                    canvas.translate(
                        -offsetX,
                        -offsetY
                    )

                    forePaint.shader?.transform {
                        setTranslate(offsetX, 0f)
                    }

                    canvas.drawPath(
                        wave.buildWavePath(
                            dp = dp,
                            width = maxWidth,
                            height = maxHeight,
                            amplitude = size.height * amplitude,
                            progress = progress
                        ), forePaint
                    )
                }
            }
        }
    }
}

@Composable
private fun InfiniteTransition.animateOf(duration: Int) = animateFloat(
    initialValue = 0f, targetValue = 1f, animationSpec = infiniteRepeatable(
        animation = tween(duration, easing = CubicBezierEasing(0.4f, 0.2f, 0.6f, 0.8f)),
        repeatMode = RepeatMode.Restart
    )
)
