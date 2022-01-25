package com.ericho.compose.demo.base

import android.view.animation.Interpolator
import androidx.compose.animation.core.Easing

class InterpolatorEasing(private val interpolator: Interpolator) : Easing {
    override fun transform(fraction: Float): Float {
        return interpolator.getInterpolation(fraction)
    }
}