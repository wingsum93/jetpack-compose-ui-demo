package com.ericho.compose.demo.model

data class DrinkModel(
    val title: String,
    val description: String,
    val star: Int = 0,
    val link: String? = null
)