package com.ericho.compose.demo.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ericho.compose.demo.base.StandardButton
import com.ericho.compose.demo.model.data2
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalGraphicsApi
@Composable
fun HomePageUi(
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        for (i in data2) {
            StandardButton(text = i.title) {
                navHostController.navigate(i.key)
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@ExperimentalGraphicsApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewHomePage() {
    val nav = rememberNavController()
    HomePageUi(navHostController = nav)
}