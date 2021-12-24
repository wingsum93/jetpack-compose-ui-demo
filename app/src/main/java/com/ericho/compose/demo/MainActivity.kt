package com.ericho.compose.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ericho.compose.demo.ui.BasicAnimationPage1
import com.ericho.compose.demo.ui.ConstraintLayoutExamplePage
import com.ericho.compose.demo.ui.animation.BasicAnimationPage2
import com.ericho.compose.demo.ui.animation.BasicAnimationPage3
import com.ericho.compose.demo.ui.theme.JetpackComposeUiDemoTheme

@ExperimentalMaterialApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeUiDemoTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Route.HOME,
                        modifier = Modifier
                    ) {
                        composable(Route.HOME) {
                            HomePageUi(navController)
                        }
                        composable(Route.CONSTRAINT_LAYOUT) {
                            ConstraintLayoutExamplePage()
                        }
                        composable(Route.BASIC_ANIMATION_VISIBILITY) {
                            BasicAnimationPage1()
                        }
                        composable(Route.BASIC_ANIMATION_CONTENT) {
                            BasicAnimationPage2()
                        }
                        composable(Route.BASIC_ANIMATION_CONTENT2) {
                            BasicAnimationPage3()
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeUiDemoTheme {
        HomePageUi(navHostController = rememberNavController())
    }
}