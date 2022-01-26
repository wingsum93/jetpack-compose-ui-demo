package com.ericho.compose.demo

import android.view.animation.BounceInterpolator
import org.junit.Assert
import org.junit.Test

class MathInterpolatorTest {

    private val interpolator = BounceInterpolator()

    @Test
    fun test_0() {
        Assert.assertEquals(0f, interpolator.getInterpolation(0f))
    }
}