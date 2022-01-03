package com.ericho.compose.demo.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ericho.compose.demo.base.StandardButton
import com.ericho.compose.demo.model.data2
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.*

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalGraphicsApi
@Composable
fun HomePageUi(
    navHostController: NavHostController
) {
    // init data analyze
    val allTags = data2.flatMap { it.tags }
    allTags.sorted()
    val scope = rememberCoroutineScope()
    var filterTag by remember { mutableStateOf<String?>(null) }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Ui Demo") }, backgroundColor = Color.White)
        }
    ) { paddingValue ->
        val filteredList =
            if (filterTag == null) data2 else data2.filter { it.tags.contains(filterTag) }
        Column(
            modifier = Modifier
                .padding(paddingValue)
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.scrollable(rememberScrollState(), Orientation.Horizontal)
            ) {
                for (oneTag in allTags) {
                    TextButton(onClick = {
                        scope.launch {
                            filterTag = oneTag
                        }
                    }) {
                        Text(text = oneTag)
                    }
                }
            }
            for (i in filteredList) {
                StandardButton(text = i.title) {
                    navHostController.navigate(i.key)
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
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