package com.ericho.compose.demo.ui.touch

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ericho.compose.demo.data.Cat
import com.ericho.compose.demo.data.CatsRepo
import com.ericho.compose.demo.data.generateRandomCats
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

@Composable
fun CatListWithSwipe(
    cats: List<Cat>,
    onAddCat: (Cat) -> Unit = {},
    onRemove: (Cat) -> Unit = {},
) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { onAddCat(generateRandomCats()) }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            item {
                Header(text = "Cats available for adoption")
            }
            items(cats, key = { it.hashCode() }) { cat ->
                CatListItem(cat = cat, onRemove = onRemove)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CatListItem(cat: Cat, onRemove: (Cat) -> Unit) {

    val state = rememberDismissState {
        if (it == DismissValue.DismissedToStart) {
            onRemove(cat)
            Log.w("1999", "swipeToDismiss !!!!")
        }
        true
    }
    SwipeToDismiss(state = state,
        directions = setOf(DismissDirection.EndToStart),
        background = {
            val color = when (state.dismissDirection) {
                DismissDirection.StartToEnd -> Color.Red
                DismissDirection.EndToStart -> Color.Black
                null -> Color.Transparent
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color)
                    .padding(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "delete",
                    tint = Color.Gray,
                    modifier = Modifier.align(
                        Alignment.CenterEnd
                    )
                )
            }
        }) {
        ListItem(
            modifier = Modifier
                .background(MaterialTheme.colors.surface)
                .padding(8.dp),
            icon = {
                Image(
                    painter = painterResource(id = cat.catImage),
                    contentDescription = null,
                    modifier = Modifier.clip(MaterialTheme.shapes.small)
                )
            },
            text = { Text(text = cat.name, style = MaterialTheme.typography.h6) },
            secondaryText = {
                Text(
                    text = cat.gender,
                    style = MaterialTheme.typography.body2,
                    color = Color.Black.copy(.5f)
                )
            }
        )
        Divider()
    }
}

@Composable
fun Header(
    text: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = MaterialTheme.colors.onSurface.copy(.1f),
        contentColor = MaterialTheme.colors.primary,
        modifier = modifier.semantics { heading() }
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.subtitle2,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CatListWithSwipePreview_single() {
    val cat = remember {
        generateRandomCats()
    }
    CatListItem(cat = cat, {})
}

@Preview
@Composable
fun CatListWithSwipePreview() {
    val cats = remember {
        CatsRepo.getCats()
    }
    CatListWithSwipe(cats, {}, {})
}
