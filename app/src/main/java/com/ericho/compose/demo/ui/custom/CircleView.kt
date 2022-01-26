package com.ericho.compose.demo.ui.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.graphics.scale
import com.ericho.compose.demo.R

//RefLink:
//https://www.jianshu.com/p/c1f11a6eef20(Angle)
//https://www.jianshu.com/p/b420dbfec73c(PorterDuffMode)
//https://stackoverflow.com/questions/23966272/android-mask-bitmap-on-canvas-gen-a-black-space(BitmapMask)
class CircleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    val hasBackgroundImg = true

    //Circle Line Width
    private var circleStrokeWidth: Float = 50f

    //Mask Circle Line Width
    private val angleWidth = 5f
    private var backgroundImage =
        BitmapFactory.decodeResource(resources, R.drawable.boardway_shape)
    private val angleList = arrayListOf(
        270 - (angleWidth / 2),
        30 - (angleWidth / 2),
        150 - (angleWidth / 2)
    )
    private val bitmapPaint = Paint()
    private val drawMaskPaint = Paint().also {
        it.color = Color.GREEN
        it.isAntiAlias = true
        it.style = Paint.Style.STROKE
        it.strokeWidth = circleStrokeWidth
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val drawImage = drawMaskBitmap(getBitmapMask())
        canvas?.apply {
            val layerId = saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null)
            when (hasBackgroundImg) {
                true -> {
                    drawBitmap(drawImage, 0f, 0f, bitmapPaint)
                }
                else -> {
                    drawMaskShape(this)
                }
            }
            restoreToCount(layerId)
        }
    }

    private fun getBitmapMask() = Bitmap.createBitmap(
        width,
        height,
        Bitmap.Config.ARGB_8888
    ).apply {
        drawMaskShape(Canvas(this))
    }

    private fun drawMaskBitmap(mask: Bitmap): Bitmap {
        // Scaled Background Image
        val original = backgroundImage.scale(width, height)

        // EmptyImage
        val result = Bitmap.createBitmap(mask.width, mask.height, Bitmap.Config.ARGB_8888)

        val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        }
        // Start Draw Mask to Background Image
        Canvas(result).apply {
            drawBitmap(original, 0f, 0f, null)
            drawBitmap(mask, 0f, 0f, paint)
        }
        return result
    }

    private fun drawMaskShape(canvas: Canvas) = canvas.apply {
        Log.d("KitMak", "$width - r:${width / 2f}")
        drawCircle(
            width / 2f,
            height / 2f,
            (canvas.width / 2f) - (circleStrokeWidth / 2f),
            drawMaskPaint
        )
        angleList.forEach { angle ->
            drawArc(0f, 0f, width.toFloat(), height.toFloat(), angle, angleWidth, true,
                Paint().apply {
                    color = Color.RED
                    isAntiAlias = true
                    this.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
                })
        }
    }
}