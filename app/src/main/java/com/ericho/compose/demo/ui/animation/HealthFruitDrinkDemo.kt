package com.ericho.compose.demo.ui.animation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import com.ericho.compose.demo.R
import com.ericho.compose.demo.model.DrinkModel
import com.ericho.compose.demo.ui.theme.JetpackComposeUiDemoTheme
import androidx.compose.foundation.layout.*

val drinkPromotion = listOf<DrinkModel>(
    DrinkModel(
        "Nutritious Drink", "The Fruit Juice That Is Way More Nutritious Than You Might Think", 500,
        "https://www.mashed.com/img/gallery/the-fruit-juice-that-is-way-more-nutritious-than-you-might-think/intro-1627652960.webp"
    ),
    DrinkModel(
        "cantaloupe juice",
        "this juicy melon has barely any calories or fat due to its high water content",
        300,
        "https://www.mashed.com/img/gallery/the-fruit-juice-that-is-way-more-nutritious-than-you-might-think/the-surprising-health-benefits-of-cantaloupe-juice-1627652960.webp"
    ),
    DrinkModel(
        "Orange juice",
        "Best for fitness",
        500,
        "https://www.mashed.com/img/gallery/5-juices-you-should-be-drinking-and-5-you-shouldnt/intro.jpg"
    ),
)

@Composable
fun HealthFruitDrinkDemo() {
    Scaffold(topBar = {
        TopAppBar {
            Text(text = "Health Fruit Drinks")
        }
    }) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(drinkPromotion, key = { it.title + it.link }) {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colors.surface),
                ) {
                    val (image, title, description, star) = createRefs()
                    Image(
                        painter = rememberImagePainter(data = it.link),
                        contentDescription = null,
                        modifier = Modifier
                            .constrainAs(image) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                            }
                            .aspectRatio(16 / 9f)
                    )

                    Text(text = it.title, modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .constrainAs(title) {
                            start.linkTo(parent.start, 10.dp)
                            top.linkTo(image.bottom, 5.dp)
                        })
                    Text(
                        text = it.description,
                        fontSize = 8.sp,
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .constrainAs(description) {
                                start.linkTo(parent.start, 10.dp)
                                top.linkTo(title.bottom)
                                bottom.linkTo(parent.bottom, 10.dp)
                            }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_star_24),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.Red),
                        modifier = Modifier
                            .size(35.dp)
                            .constrainAs(star) {
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                            }
                    )

                }
            }
        }
    }
}


@Preview
@Composable
fun HealthFruitDrinkDemoPreview() {
    JetpackComposeUiDemoTheme {
        HealthFruitDrinkDemo()
    }
}