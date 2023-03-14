package com.swayy.settings.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination

interface ProfileNavigator {

    fun openSettings()

    fun popBackStack()

}


@Destination
@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(14.dp))
            androidx.compose.material3.Text(
                text = "More",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold,
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(14.dp))
                Divider(color = Color.LightGray.copy(alpha = 0.6f), thickness = 0.5.dp)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(Color.LightGray.copy(alpha = .0f)),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    androidx.compose.material3.Text(
                        text = "About",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 17.sp,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 12.dp),
                        fontWeight = FontWeight.Bold,
                    )
                    Image(
                        painter = painterResource(id = com.swayy.core.R.drawable.baseline_arrow_forward_ios_24),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(end = 20.dp)
                            .size(20.dp),
                        colorFilter = ColorFilter.tint(Color.Gray)
                    )
                }
                Divider(color = Color.LightGray.copy(alpha = 0.6f), thickness = 0.5.dp)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(Color.LightGray.copy(alpha = .0f)),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    androidx.compose.material3.Text(
                        text = "Blog",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 17.sp,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 12.dp),
                        fontWeight = FontWeight.Bold,
                    )
                    Image(
                        painter = painterResource(id = com.swayy.core.R.drawable.baseline_arrow_forward_ios_24),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(end = 20.dp)
                            .size(20.dp),
                        colorFilter = ColorFilter.tint(Color.Gray)
                    )
                }
                Divider(color = Color.LightGray.copy(alpha = 0.6f), thickness = 0.5.dp)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(Color.LightGray.copy(alpha = .0f)),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    androidx.compose.material3.Text(
                        text = "Help Center",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 17.sp,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 12.dp),
                        fontWeight = FontWeight.Bold,
                    )
                    Image(
                        painter = painterResource(id = com.swayy.core.R.drawable.baseline_arrow_forward_ios_24),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(end = 20.dp)
                            .size(20.dp),
                        colorFilter = ColorFilter.tint(Color.Gray)
                    )
                }
                Divider(color = Color.LightGray.copy(alpha = 0.6f), thickness = 0.5.dp)
                Spacer(modifier = Modifier.height(16.dp))
                androidx.compose.material3.Text(
                    text = "Follow us",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(start = 12.dp),
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(10.dp))
                FilterOptionsComponent()
            }

        }
    }
}


@Composable
fun FilterOptionsComponent() {
    val filterOptions = FILTER_CONTENT_LIST

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(top = 4.dp, start = 4.dp, end = 4.dp)
            .height(400.dp),
        content = {
            items(filterOptions.size) {
                ChipComponent(filter = filterOptions[it])
            }
        })
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipComponent(filter: FilterContent) {
    val backgroundColor = filter.contentColor
    val logo = filter.logo
    val filterText = filter.filterText

    Box(
        modifier = Modifier
            .size(290.dp, 200.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp), shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = backgroundColor))
                    .clip(
                        RoundedCornerShape(10.dp)
                    )
            ) {

                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(
                                start =
                                12.dp, bottom = 20.dp
                            )
                    ) {
                        Image(
                            painter = painterResource(id = logo),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 20.dp)
                                .size(30.dp),
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        androidx.compose.material3.Text(
                            text = filterText,
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge,
                            fontSize = 17.sp,
                            modifier = Modifier,
                            fontWeight = FontWeight.Bold,
                        )
                    }

                }

            }
        }
    }
}


data class FilterContent(
    val contentColor: Int,
    val logo: Int,
    val filterText: String
)

val FILTER_CONTENT_LIST = listOf(
    FilterContent(com.swayy.core.R.color.twitter, com.swayy.core.R.drawable.twitter, "Twitter"),
    FilterContent(
        com.swayy.core.R.color.instagram,
        com.swayy.core.R.drawable.instagram,
        "Instagram"
    ),
    FilterContent(com.swayy.core.R.color.discord, com.swayy.core.R.drawable.discord, "Discord"),
    FilterContent(com.swayy.core.R.color.reddit, com.swayy.core.R.drawable.reddit, "Reddit"),
    FilterContent(com.swayy.core.R.color.youtube, com.swayy.core.R.drawable.you, "Youtube")
)