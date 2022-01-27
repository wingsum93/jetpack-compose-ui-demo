package com.ericho.compose.demo.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout

@Composable
fun SingleTagFilterV2(
    modifier: Modifier = Modifier,
    tagState: SingleTagState = rememberSingleTagState(),
    content: @Composable () -> Unit = {}
) {
    val tags = tagState.tags
    val selectedTag = tagState.selectedTag
    val onTagSelect = tagState.onTagChange
    val innerTags = tags.toMutableList()
    innerTags.removeAll { it == selectedTag }
    if (selectedTag != null) innerTags.add(0, selectedTag)

    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        // Don't constrain child views further, measure them with given constraints
        // List of measured children
        val placeables = measurables.map { measurable ->
            // Measure each children
            measurable.measure(constraints)
        }
        // Set the size of the layout as big as it can
        layout(constraints.maxWidth, constraints.maxHeight) {
            // Track the y co-ord we have placed children up to
            var yPosition = 0

            // Place children in the parent layout
            placeables.forEach { placeable ->
                // Position item on the screen
                placeable.placeRelative(x = 0, y = yPosition)

                // Record the y co-ord placed up to
                yPosition += placeable.height
            }
        }
    }
}