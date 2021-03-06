package com.ericho.compose.demo.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ericho.compose.demo.base.SingleTagFilter
import com.ericho.compose.demo.base.StandardIconButton
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
    // init data analyze
    val allTags = data2.flatMap { it.tags }
    val distinctTags = allTags.distinct().sorted()
    var filterTag by rememberSaveable { mutableStateOf<String?>(null) }
    val filteredList = remember(key1 = filterTag) {
        if (filterTag == null) data2 else data2.filter { it.tags.contains(filterTag) }
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Ui Demo") }, backgroundColor = Color.White)
        }
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .padding(paddingValue)
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            SingleTagFilter(tags = distinctTags,
                onTagSelect = {
                    filterTag = it
                },
                onTagReset = {
                    filterTag = null
                })
            for (i in filteredList) {
                StandardIconButton(text = i.title) {
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