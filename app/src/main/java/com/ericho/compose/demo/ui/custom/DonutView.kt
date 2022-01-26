package com.ericho.compose.demo.ui.custom

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.annotation.ColorInt
import androidx.core.graphics.drawable.toBitmap
import kotlin.math.min

/**
 * 1) animation
 * 2) mask
 * 3) store in assets
 */
class DonutView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val maskPaint by lazy {
        Paint().apply {
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeWidth = this@DonutView.strokeWidth
        }
    }
    private val backgroundPaint by lazy {
        Paint().apply {
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeWidth = this@DonutView.strokeWidth
        }
    }
    private var strokeWidth = 50f
        set(value) {
            field = value
            maskPaint.strokeWidth = value
            backgroundPaint.strokeWidth = value
        }
    private var progress: Float = 1f
    private var currentAnimationProgress: Float = 0f // 0..1
    var currentBackgroundAnimationProgress: Float = 0f // 0..1
    private var section: Int = 1
    private var sectionSpaceAngle: Float = 3f

    private var animator: ValueAnimator? = null
    private var backgroundAnimator: ValueAnimator? = null
    private var animatorSet: AnimatorSet? = null
    private var backgroundInterpolator = BounceInterpolator()
    private var foregroundInterpolator = DecelerateInterpolator()
    private var donutBackgroundColor: Int = 0x7F000000
        set(value) {
            field = value
            backgroundPaint.color = value
        }
    private var donutForegroundDrawable: Drawable? = null
        set(value) {
            field = value
            updateBitmapShaderIfNeeded()
        }
//    private var donutMaskDrawable: Drawable? = null // TODO: should have default value

    fun startAnimation() {
        resetBackgroundAndProgress()
        animatorSet?.cancel()

        backgroundAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
            addUpdateListener {
                currentBackgroundAnimationProgress = it.animatedValue as Float
                invalidate()
            }
            duration = 1000
            interpolator = backgroundInterpolator
        }

        animator = ValueAnimator.ofFloat(0f, progress)
            .apply {
                addUpdateListener {
                    currentAnimationProgress = it.animatedValue as Float
                    invalidate()
                }
                duration = 1000
                interpolator = foregroundInterpolator
            }
        animatorSet = AnimatorSet().apply {
            play(animator).after(backgroundAnimator)
            start()
        }
    }

    private fun createForegroundValueAnimator(): ValueAnimator {
        return ValueAnimator.ofFloat(0f, progress)
            .apply {
                addUpdateListener {
                    currentAnimationProgress = it.animatedValue as Float
                    invalidate()
                }
                duration = 1000
                interpolator = foregroundInterpolator
            }
    }

    fun startOnlyForegroundAnimation() {
        animatorSet?.cancel()
        resetBackgroundAndProgress()
        //
        animator = createForegroundValueAnimator()
        animatorSet = AnimatorSet().apply {
            play(animator)
            start()
        }
    }

    fun setProgress(progress: Float) {
        this.progress = progress
    }

    fun setDonutSection(section: Int) {
        this.section = section
    }

    fun setDonutSectionSpaceAngle(sectionSpaceAngle: Float) {
        this.sectionSpaceAngle = sectionSpaceAngle
    }

    fun setDonutBackground(@ColorInt color: Int) {
        donutBackgroundColor = color
    }

    fun setDonutForeground(drawable: Drawable) {
        donutForegroundDrawable = drawable
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        updateBitmapShaderIfNeeded()
    }

    private fun updateBitmapShaderIfNeeded() {
        try {
            val foregroundBitmapShader = donutForegroundDrawable
                ?.toBitmap(right - left, bottom - top)
                ?.toBitmapShader()

            maskPaint.shader = foregroundBitmapShader
        } catch (e: Exception) {
            // Do nothing
        }
    }

    private fun resetBackgroundAndProgress() {
        currentBackgroundAnimationProgress = 0f
        currentAnimationProgress = 0f
        invalidate()
    }

    fun setDonutStrokeWidth(px: Float) {
        strokeWidth = px
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return
        val minLength = min(width - strokeWidth, height - strokeWidth).toFloat()
        val leftOffset = (width - minLength) / 2
        val topOffset = (height - minLength) / 2
        val rect = RectF(leftOffset, topOffset, width - leftOffset, height - topOffset)
        val eachSectionProgress = 1f / section
        for (i in 0 until section) {
            val startSectionProgress = i * eachSectionProgress
            val startPointOfSection = i * 360 / section - 90f
            val sectionAngle = (360 / section) - sectionSpaceAngle
            val backgroundSectionAngle =
                (currentBackgroundAnimationProgress * 360 - i * 360 / section).coerceIn(
                    0f,
                    (360 / section).toFloat()
                )
            val targetSweepAngle = backgroundSectionAngle / (360 / section) * sectionAngle
            canvas.drawArc(rect, startPointOfSection, targetSweepAngle, false, backgroundPaint)
            Log.i("eric1999", "onDraw: targetSweepAngle-> $targetSweepAngle")
            if (currentAnimationProgress > startSectionProgress) {
                // should draw
                val consumingSectionAngle =
                    min(currentAnimationProgress - startSectionProgress, eachSectionProgress)
                val drawAngleInThisSection =
                    sectionAngle * consumingSectionAngle / eachSectionProgress
                canvas.drawArc(rect, startPointOfSection, drawAngleInThisSection, false, maskPaint)
            }
        }
    }

    private fun Bitmap.toBitmapShader(
        tileX: Shader.TileMode = Shader.TileMode.REPEAT,
        tileY: Shader.TileMode = Shader.TileMode.REPEAT
    ): BitmapShader {
        return BitmapShader(this, tileX, tileY)
    }
}
