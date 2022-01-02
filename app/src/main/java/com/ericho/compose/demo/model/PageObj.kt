package com.ericho.compose.demo.model

class PageObj(
    var title: String,
    var key: String,
    var tags: MutableList<String> = mutableListOf()
) {
    companion object {
        fun create(title: String, key: String, vararg tags: String): PageObj {
            return PageObj(
                title = title,
                key = key,
                tags = tags.toMutableList()
            )
        }
    }
}