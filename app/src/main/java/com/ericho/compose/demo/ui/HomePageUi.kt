package com.ericho.compose.demo.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ericho.compose.demo.Route
import com.ericho.compose.demo.base.StandardButton
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

val data = listOf<Pair<String, String>>(
    "Constraint Layout" to Route.CONSTRAINT_LAYOUT,
    "Animation Color" to Route.ANIMATION_COLOR,
    "Basic animation (Animation Visibility)" to Route.BASIC_ANIMATION_VISIBILITY,
    "Basic animation (Animation Content)" to Route.BASIC_ANIMATION_CONTENT,
    "Basic animation (Animation Content2)" to Route.BASIC_ANIMATION_CONTENT2,
    "Basic animation (Cross Fade)" to Route.BASIC_ANIMATION_CROSS_FADE,
    "Low Level animation (animate*AsState)" to Route.LOW_LEVEL_ANIMATION_API_1,
    "Infinite Animation" to Route.INFINITE_ANIMATION,
    "Custom animation (Fab with rolling down number) IP" to Route.CUSTOM_ANIMATION_1
)

@Composable
fun HomePageUi(
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        for (i in data) {
            StandardButton(text = i.first) {
                navHostController.navigate(i.second)
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Preview
@Composable
fun PreviewHomePage() {
    val nav = rememberNavController()
    HomePageUi(navHostController = nav)
}