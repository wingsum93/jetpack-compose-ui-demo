package com.ericho.compose.demo.base

class SingleTagState(
    val tags: List<String> = listOf(),
    private val _onTagChange: (String?) -> Unit = {}

) {
    var selectedTagIndex = -1
    fun hasSelectedTag(): Boolean = tags.isNotEmpty() && selectedTagIndex != -1

    val selectedTag: String? = if (hasSelectedTag()) tags[selectedTagIndex] else null

    private val onTagSelect: (Int) -> Unit = {
        if (it == -1) onTagChange(null) else onTagChange(tags[it])
    }

    val onTagChange: (String?) -> Unit = logic@{
        //check any change of tag?
        if (it == selectedTag) return@logic

        if (it == null) {
            selectedTagIndex = -1
        } else {
            tags.forEachIndexed { index, tag ->
                if (tag == it) selectedTagIndex = index
            }
        }
        _onTagChange.invoke(it)
    }
}