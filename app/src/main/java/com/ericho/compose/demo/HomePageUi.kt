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

val data = listOf("Stack Layout", "Animation Color", "Other animation")

@Composable
fun HomePageUi(
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        for (i in data) {
            StandardButton(text = i)
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