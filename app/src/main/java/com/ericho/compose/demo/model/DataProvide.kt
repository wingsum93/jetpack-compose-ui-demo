package com.ericho.compose.demo.model

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import com.ericho.compose.demo.Route
import com.ericho.compose.demo.ui.ConstraintLayoutExamplePage
import com.ericho.compose.demo.ui.animation.*
import com.ericho.compose.demo.ui.touch.GestureAnimationPage1
import com.ericho.compose.demo.ui.touch.GestureAnimationPage2

@ExperimentalGraphicsApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
val data2 = listOf(
    PageObj.create("Constraint Layout", Route.CONSTRAINT_LAYOUT, "custom layout") {
        ConstraintLayoutExamplePage()
    },
    PageObj.create("Animation Color", Route.ANIMATION_COLOR, "animation") {
        AnimateColorPage()
    },
    PageObj.create(
        "Basic animation (Animation Visibility)",
        Route.BASIC_ANIMATION_VISIBILITY,
        "animation"
    ) {
        BasicAnimationVisibilityPage()
    },
    PageObj.create(
        "Basic animation (Animation Content)",
        Route.BASIC_ANIMATION_CONTENT,
        "animation"
    ) {
        BasicAnimationContentPage()
    },
    PageObj.create(
        "Basic animation (Animation Content2)",
        Route.BASIC_ANIMATION_CONTENT2,
        "animation"
    ) {
        BasicAnimationContentPage2()
    },
    PageObj.create("Basic animation (Cross Fade)", Route.BASIC_ANIMATION_CROSS_FADE, "animation") {
        AnimationCrossFadePage()
    },
    PageObj.create(
        "Low Level animation (animate*AsState)",
        Route.LOW_LEVEL_ANIMATION_API_1,
        "animation"
    ) {
        LowLevelAnimationApiPage()
    },
    PageObj.create("Infinite Animation", Route.INFINITE_ANIMATION, "animation") {
        InfiniteAnimationPage()
    },
    PageObj.create(
        "Custom animation (Fab with rolling down number) IP",
        Route.CUSTOM_ANIMATION_1,
        "animation"
    ) {
        CustomAnimationFabNumberRollingDown()
    },
    PageObj.create("Gesture and animation 1", Route.GESTURE_ANIMATION_1, "animation", "gesture") {
        GestureAnimationPage1()
    },
    PageObj.create("Gesture and animation 2", Route.GESTURE_ANIMATION_2, "animation", "gesture") {
        GestureAnimationPage2()
    }

)