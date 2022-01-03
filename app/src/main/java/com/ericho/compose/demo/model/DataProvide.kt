package com.ericho.compose.demo.model

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import com.ericho.compose.demo.Route
import com.ericho.compose.demo.ui.ConstraintLayoutExamplePage
import com.ericho.compose.demo.ui.animation.*
import com.ericho.compose.demo.ui.touch.*

@ExperimentalGraphicsApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
val data2 = listOf(
    PageObj.create("Constraint Layout", Route.CONSTRAINT_LAYOUT, Tags.custom_layout) {
        ConstraintLayoutExamplePage()
    },
    PageObj.create("Animation Color", Route.ANIMATION_COLOR, Tags.animation) {
        AnimateColorPage()
    },
    PageObj.create(
        "Basic animation (Animation Visibility)",
        Route.BASIC_ANIMATION_VISIBILITY,
        Tags.animation
    ) {
        BasicAnimationVisibilityPage()
    },
    PageObj.create(
        "Basic animation (Animation Content)",
        Route.BASIC_ANIMATION_CONTENT,
        Tags.animation
    ) {
        BasicAnimationContentPage()
    },
    PageObj.create(
        "Basic animation (Animation Content2)",
        Route.BASIC_ANIMATION_CONTENT2,
        Tags.animation
    ) {
        BasicAnimationContentPage2()
    },
    PageObj.create(
        "Basic animation (Cross Fade)",
        Route.BASIC_ANIMATION_CROSS_FADE,
        Tags.animation
    ) {
        AnimationCrossFadePage()
    },
    PageObj.create(
        "Low Level animation (animate*AsState)",
        Route.LOW_LEVEL_ANIMATION_API_1,
        Tags.animation
    ) {
        LowLevelAnimationApiPage()
    },
    PageObj.create("Infinite Animation", Route.INFINITE_ANIMATION, Tags.animation) {
        InfiniteAnimationPage()
    },
    PageObj.create(
        "Custom animation (Fab with rolling down number) IP",
        Route.CUSTOM_ANIMATION_1,
        Tags.animation
    ) {
        CustomAnimationFabNumberRollingDown()
    },
    PageObj.create(
        "Gesture and animation 1",
        Route.GESTURE_ANIMATION_1,
        Tags.animation,
        Tags.gesture
    ) {
        GestureAnimationPage1()
    },
    PageObj.create(
        "Gesture and animation 2",
        Route.GESTURE_ANIMATION_2,
        Tags.animation,
        Tags.gesture
    ) {
        GestureAnimationPage2()
    },
    PageObj.create(
        "Gesture and animation 3",
        Route.GESTURE_ANIMATION_3,
        Tags.animation,
        Tags.gesture
    ) {
        GestureAnimationPage3()
    },
    PageObj.create("TapGestureDemo", Route.GESTURE_DEMO, Tags.gesture) {
        TapGestureDemo()
    },
    PageObj.create("DragGestureDemo", Route.DragGestureDemo, Tags.gesture) {
        DragGestureDemo()
    },
    PageObj.create("TransformGestureDemo", Route.TransformGestureDemo, Tags.gesture) {
        TransformGestureDemo()
    },
    PageObj.create("BaseDragGestureDemo", Route.BaseDragGestureDemo, Tags.gesture) {
        BaseDragGestureDemo()
    },
)

object Tags {
    const val animation = "animation"
    const val gesture = "gesture"
    const val custom_layout = "custom layout"
}