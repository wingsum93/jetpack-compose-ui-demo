package com.ericho.compose.demo.model

class PageObj(
    var title: String,
    var key: String,
    var tags: MutableList<String> = mutableListOf(),
    val invoke: CustomPageInvoke = {}
) {
    companion object {
        fun create(
            title: String,
            key: String,
            vararg tags: String,
            invoke: CustomPageInvoke = {}
        ): PageObj {
            return PageObj(
                title = title,
                key = key,
                tags = tags.toMutableList(),
                invoke = invoke
            )
        }
    }
}