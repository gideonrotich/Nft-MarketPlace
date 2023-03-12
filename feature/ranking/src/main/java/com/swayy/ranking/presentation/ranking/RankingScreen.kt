package com.swayy.ranking.presentation.ranking

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.swayy.core.R
import com.swayy.ranking.presentation.ranking.state.ExchangeState
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode

interface RankingNavigator {
    fun openRanking()

    fun popBackStack()

}

private fun BigDecimal.scaleForDisplay(newScale: Int = 2): String {
    return this.setScale(newScale, RoundingMode.HALF_UP).toString()
}

@OptIn(ExperimentalPagerApi::class)
@Destination
@Composable
fun RankingScreen(
    navigator: RankingNavigator,
    viewModel: RankingViewModel = hiltViewModel()
) {
    val rankingState = viewModel.ranking.value
    val singleState = viewModel.single.value
    val exchangeState = viewModel.exchange.value
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(14.dp))
        androidx.compose.material3.Text(
            text = "Stats",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
        )
        val tabRowItems = listOf(
            TabRowItem(
                title = "Rankings",
                screen = { StatDetail(exchangeState = exchangeState) },
                icon = R.drawable.baseline_equalizer_24,
            ),
            TabRowItem(
                title = "Activity",
                screen = { },
                icon = R.drawable.baseline_show_chart_24,
            )
        )

        val pagerState = rememberPagerState()

        Column(
            modifier = Modifier
                .padding(0.dp)
        ) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                        color = MaterialTheme.colorScheme.secondary
                    )
                },
                backgroundColor = Color.LightGray.copy(alpha = .0F),
            ) {
                tabRowItems.forEachIndexed { index, item ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },

                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = item.icon),
                                    contentDescription = "",
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(Modifier.width(4.dp))
                                androidx.compose.material3.Text(
                                    text = item.title,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    )
                }
            }
            HorizontalPager(
                count = tabRowItems.size,
                state = pagerState,
            ) {
                tabRowItems[pagerState.currentPage].screen()
            }
        }
        Spacer(modifier = Modifier.height(14.dp))


    }
}

@Composable
private fun CollectionStatItem(
    label: String,
    value: String,
    valueColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    modifier: Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        androidx.compose.material3.Text(
            text = label,
            color = Color.DarkGray,
            fontWeight = FontWeight.Light,
            fontSize = 13.sp
        )
        androidx.compose.material3.Text(
            text = value,
            color = valueColor,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

data class TabRowItem(
    val title: String,
    val icon: Int,
    val screen: @Composable () -> Unit,
)

@Composable
fun StatDetail(
    exchangeState: ExchangeState
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(8.dp))
            FilterOptionsComponent()
            Spacer(modifier = Modifier.height(14.dp))
            Divider(color = Color.LightGray.copy(alpha = 0.6f), thickness = 0.5.dp)
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                if (exchangeState.isLoading){
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterVertically).padding(top = 20.dp).size(28.dp),
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        strokeWidth = 2.6.dp
                    )
                }
            }

            LazyColumn() {
                itemsIndexed(exchangeState.exchange) { index, data ->
                    var isExpanded by remember { mutableStateOf(false) }
                    val isPositive = data.stats?.total_volume

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                            .padding(vertical = 12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            androidx.compose.material3.Text(
                                text = "${index + 1}",
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.Bold
                            )
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = Color.LightGray,
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .size(56.dp)
                            ) {
                                AsyncImage(
                                    model = data.image_url,
                                    contentDescription = "collection-image",
                                    contentScale = ContentScale.Crop
                                )
                            }

                            Column(modifier = Modifier.weight(0.2f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    androidx.compose.material3.Text(
                                        text = data.name!!,
                                        modifier = Modifier
                                            .weight(0.2f)
                                            .padding(top = 12.dp),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                }
                                TextButton(onClick = { isExpanded = !isExpanded }) {
                                    androidx.compose.material3.Text(
                                        text = if (isExpanded) "- less" else "+ more",
                                        color = Color.DarkGray
                                    )
                                }
                            }

                            Column(
                                horizontalAlignment = Alignment.End,
                                modifier = Modifier.padding(start = 4.dp)
                            ) {
                                androidx.compose.material3.Text(
                                    text = data.stats?.seven_day_sales.toString() + " ETH",
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontSize = 16.sp
                                )
                                androidx.compose.material3.Text(
                                    text = data.stats?.thirty_day_volume_change?.toBigDecimal()
                                        ?.scaleForDisplay() + "%",
                                    color = if (data.stats?.thirty_day_volume_change!!.toBigDecimal() >= BigDecimal.ZERO) Color.Green else Color.Red,
                                    fontSize = 14.sp
                                )
                            }
                        }

                        AnimatedVisibility(visible = isExpanded) {
                            Column {
                                Spacer(
                                    modifier = Modifier
                                        .padding(all = 16.dp)
                                        .background(color = Color.LightGray)
                                        .height(0.5.dp)
                                        .fillMaxWidth()
                                )

                                val collectionStats = mapOf(
                                    "Floor Price" to "${data.stats?.floor_price}",
                                    "Owners" to "${data.stats?.num_owners}",
                                    "Assets" to "${data.stats?.total_minted}"
                                )

                                Row {
                                    CollectionStatItem(
                                        label = "24h%",
                                        value = data.stats?.total_minted.toString() + "%",
                                        modifier = Modifier.weight(1f),
                                        valueColor = Color.Green
                                    )
                                    collectionStats.forEach { mapEntry ->
                                        CollectionStatItem(
                                            label = mapEntry.key,
                                            value = mapEntry.value,
                                            modifier = Modifier.weight(1f)
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Divider(color = Color.LightGray.copy(alpha = 0.6f), thickness = 0.5.dp)
                }
            }
        }
    }
}

val FILTER_CONTENT_LIST = listOf(
    FilterContent(
        Color.White,
        Color.LightGray.copy(0.4f),
        "All Categories",
        R.drawable.baseline_keyboard_arrow_down_24
    ),
    FilterContent(
        Color.Black,
        Color.LightGray.copy(0.4f),
        "All Chains",
        R.drawable.baseline_keyboard_arrow_down_24
    ),
    FilterContent(
        Color.Black,
        Color.LightGray.copy(0.4f),
        "Market",
        R.drawable.baseline_keyboard_arrow_down_24
    ),
)

@Composable
fun FilterOptionsComponent() {
    val filterOptions = FILTER_CONTENT_LIST
    LazyRow(
        Modifier.padding(top = 4.dp, start = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(filterOptions.size) {
            ChipComponent(filter = filterOptions[it])
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipComponent(filter: FilterContent) {
    val contentColor = filter.contentColor
    val chipBackground = Color.White
    val filterText = filter.filterText

    Chip(
        onClick = { /*TODO*/ },
        colors = ChipDefaults.chipColors(
            contentColor = contentColor,
            backgroundColor = chipBackground
        ),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.4f)),
        modifier = Modifier.height(46.dp)
    ) {
        Row(modifier = Modifier.height(30.dp)) {
            Spacer(modifier = Modifier.width(6.dp))
            androidx.compose.material3.Text(
                text = filterText,
                modifier = Modifier.align(Alignment.CenterVertically),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(6.dp))
            androidx.compose.material3.Icon(
                painter = painterResource(id = filter.Icon),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 6.dp),
                tint = Color.Black
            )
        }
    }
}


data class FilterContent(
    val contentColor: Color,
    val backgroundColor: Color,
    val filterText: String,
    val Icon: Int

)