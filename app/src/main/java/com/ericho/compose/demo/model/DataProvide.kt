package com.ericho.compose.demo.model

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import com.ericho.compose.demo.Route
import com.ericho.compose.demo.data.CatsRepo
import com.ericho.compose.demo.ui.ConstraintLayoutExamplePage
import com.ericho.compose.demo.ui.animation.*
import com.ericho.compose.demo.ui.custom.ClickEventOverlayDemo
import com.ericho.compose.demo.ui.touch.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
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
    PageObj.create(
        "DragGestureDemo (detectDragGestures)",
        Route.DragGestureDemo,
        Tags.gesture
    ) {
        DragGestureDemo()
    },
    PageObj.create("TransformGestureDemo", Route.TransformGestureDemo, Tags.gesture) {
        TransformGestureDemo()
    },
    PageObj.create(
        "BaseDragGestureDemo (use low level gesture api - awaitDragOrCancellation)",
        Route.BaseDragGestureDemo,
        Tags.gesture
    ) {
        BaseDragGestureDemo()
    },
    PageObj.create(
        "ClickEventOverlayDemo (test click event overlay)",
        Route.ClickEventOverlayDemo,
        Tags.ui
    ) {
        ClickEventOverlayDemo()
    },
    PageObj.create(
        "HealthFruitDrinkDemo (animate star icon in list)",
        Route.ClickEventOverlayDemo,
        Tags.animation
    ) {
        HealthFruitDrinkDemo()
    },
    PageObj.create(
        "CatListWithSwipe (swipe to delete in list)",
        Route.CatListWithSwipe,
        Tags.gesture
    ) {
        var list by remember {
            mutableStateOf(CatsRepo.getCats())
        }
        CatListWithSwipe(
            list, { cat ->
                list = list.toMutableList().also { it.add(cat) }
            }, { cat ->
                list = list.toMutableList().also { it.remove(cat) }
            })
    },
    PageObj.create(
        "AnimateGraphicLayerDemo (animate rotation XY in graphicsLayer)",
        Route.AnimateGraphicLayerDemo,
        Tags.animation
    ) {
        AnimateGraphicLayerDemo()
    },
)

object Tags {
    const val animation = "animation"
    const val gesture = "gesture"
    const val custom_layout = "custom layout"
    const val ui = "ui"
}