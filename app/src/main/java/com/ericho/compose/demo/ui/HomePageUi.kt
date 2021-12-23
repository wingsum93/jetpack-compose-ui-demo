package com.ericho.compose.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ericho.compose.demo.base.StandardButton

val data = hashMapOf<String, String>(
    "Stack Layout" to Route.STACK_LAYOUT,
    "Animation Color" to Route.ANIMATION_COLOR,
    "Basic animation" to Route.BASIC_ANIMATION
)

@Composable
fun HomePageUi(
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        for (i in data) {
            StandardButton(text = i.key) {
                navHostController.navigate(i.value)
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