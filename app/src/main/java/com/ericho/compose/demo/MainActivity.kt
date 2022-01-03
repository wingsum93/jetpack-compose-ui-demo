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
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ericho.compose.demo.model.data2
import com.ericho.compose.demo.ui.HomePageUi
import com.ericho.compose.demo.ui.theme.JetpackComposeUiDemoTheme


@ExperimentalGraphicsApi
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
                        for (d in data2) {
                            composable(d.key) {
                                d.invoke.invoke(it)
                            }
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalGraphicsApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeUiDemoTheme {
        HomePageUi(navHostController = rememberNavController())
    }
}