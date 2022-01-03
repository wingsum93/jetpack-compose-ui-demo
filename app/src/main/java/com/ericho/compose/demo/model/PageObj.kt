package com.ericho.compose.demo.model

class PageObj(
    var title: String,
    var key: String,
    var tags: MutableList<String> = mutableListOf(),
    val init: CustomPageInit = {}
) {
    companion object {
        fun create(
            title: String,
            key: String,
            vararg tags: String,
            init: CustomPageInit = {}
        ): PageObj {
            return PageObj(
                title = title,
                key = key,
                tags = tags.toMutableList(),
                init = init
            )
        }
    }
}