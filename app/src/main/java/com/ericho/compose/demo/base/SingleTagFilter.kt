package com.ericho.compose.demo.base

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding

@Composable
fun SingleTagFilter(
    tagState: SingleTagState = rememberSingleTagState()
) {
    val tags = tagState.tags
    val selectedTag = tagState.selectedTag
    val onTagSelect = tagState.onTagChange
    val innerTags = tags.toMutableList()
    innerTags.removeAll { it == selectedTag }
    if (selectedTag != null) innerTags.add(0, selectedTag)
    Row(modifier = Modifier) {
        Text(
            text = "Filter by:",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(5.dp, 0.dp)
        )
        LazyRow(
            modifier = Modifier
                .weight(1f)
        ) {
            itemsIndexed(items = innerTags, itemContent = { index, tag ->
                val isSelected = index == tagState.selectedTagIndex
                val colors =
                    if (isSelected) ButtonDefaults.textButtonColors(
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = Color.White
                    )
                    else
                        ButtonDefaults.textButtonColors()
                TextButton(
                    colors = colors,
                    border = BorderStroke(1.dp, MaterialTheme.colors.primary),
                    modifier = Modifier.padding(3.dp, 0.dp),
                    onClick = {
                        onTagSelect(tag)
                    }) {
                    Text(
                        text = tag, color = colors.contentColor(enabled = true).value,
                        fontSize = MaterialTheme.typography.caption.fontSize
                    )
                }
            })
        }
        if (selectedTag != null) {
            Button(onClick = {
                onTagSelect(null)
            }) {
                Image(
                    imageVector = Icons.Default.Close, contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
        }
    }

}

@Composable
fun rememberSingleTagState(
    tagList: List<String> = listOf(),
    onTagChange: (String?) -> Unit = {}
): SingleTagState {
    val state = SingleTagState(tagList, onTagChange)
    return remember(key1 = tagList, key2 = state) {
        state
    }
}

@Preview
@Composable
fun SingleTagFilterPreview() {
    val sampleTags = listOf("first tag", "second tag")
    SingleTagFilter(rememberSingleTagState(tagList = sampleTags))
}