package com.ericho.compose.demo.base

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row

@Composable
fun TagFilter(
    tags: List<String>,
    onTagSelect: (String) -> Unit = {},
    onTagReset: () -> Unit = {}
) {
    var selectedTag by remember { mutableStateOf<String?>(null) }
    val innerTags = tags.toMutableList()
    innerTags.removeAll { it == selectedTag }
    if (selectedTag != null) innerTags.add(0, selectedTag!!)
    Row(modifier = Modifier) {
        Text(
            text = "Filter by:",
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        LazyRow(
            modifier = Modifier
                .weight(1f)
        ) {
            items(items = innerTags, itemContent = { tag ->
                val isSelected = tag == selectedTag
                val colors =
                    if (isSelected) ButtonDefaults.textButtonColors(
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = Color.White
                    )
                    else
                        ButtonDefaults.textButtonColors()
                TextButton(
                    colors = colors,
                    border = BorderStroke(2.dp, MaterialTheme.colors.primary),
                    onClick = {
                        selectedTag = tag
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
            TextButton(onClick = {
                onTagReset.invoke()
                selectedTag = null
            }) {
                Text(text = "Reset")
            }
        }
    }

}

@Preview
@Composable
fun TagFilterPreview() {
    val sampleTags = listOf("first tag", "second tag")
    TagFilter(sampleTags)
}