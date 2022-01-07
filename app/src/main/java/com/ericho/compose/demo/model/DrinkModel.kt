package com.ericho.compose.demo.model

data class DrinkModel(
    val title: String,
    val description: String,
    val star: Int = 0,
    var like: Boolean = false,
    val link: String? = null
)

fun createDrinkModel(
    title: String,
    description: String,
    star: Int,
    link: String?
): DrinkModel {
    return DrinkModel(title, description, star, link = link)
}