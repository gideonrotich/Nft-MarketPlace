package com.swayy.ranking.presentation.exchange_details

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.google.android.material.tabs.TabLayout.Tab
import com.ramcosta.composedestinations.annotation.Destination
import com.swayy.core.R
import com.swayy.ranking.presentation.exchange_details.components.ItemsScreen
import com.swayy.ranking.presentation.ranking.*
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@OptIn(ExperimentalPagerApi::class)
@Destination
@Composable
fun ExchangeDetailScreen(
    collectionId: String,
    navigator: RankingNavigator,
    viewModel: RankingViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.getSingle(key = collectionId, chain = "eth-main", exchange = "opensea")
    }

    val collectionState = viewModel.single.value
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    val state = rememberCollapsingToolbarScaffoldState()
    val textSize = (18 + (30 - 18) * state.toolbarState.progress).sp

    CollapsingToolbarScaffold(
        modifier = Modifier.fillMaxSize(),
        state = state,
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbarModifier = Modifier.background(MaterialTheme.colorScheme.background),
        enabled = true,
        toolbar = {

            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxWidth()
                    .height(230.dp)
                    .pin()
            )
            collectionState.single?.let { it ->

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.BottomStart)
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 50.dp)
                                .parallax(ratio = 0.5f)
                                .graphicsLayer {
                                    alpha = if (textSize.value == 18f) 0f else 1f
                                },
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(data = it.banner_image_url)
                                    .apply(block = fun ImageRequest.Builder.() {
                                        placeholder(R.drawable.placeholder)
                                    }).build()
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }

                    Image(
                        modifier = Modifier
                            .size(130.dp)
                            .align(Alignment.BottomStart)
                            .padding(start = 20.dp, top = 20.dp)
                            .border(
                                width = 4.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .clip(RoundedCornerShape(10.dp))
                            .parallax(ratio = 0.5f)
                            .graphicsLayer {
                                alpha = if (textSize.value == 18f) 0f else 1f
                            },
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(data = it.image_url)
                                .apply(block = fun ImageRequest.Builder.() {
                                    placeholder(R.drawable.placeholder)
                                }).build()
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )

                }

                it.name?.let { it1 ->
                    Text(
                        text = it1,
                        modifier = Modifier
                            .road(Alignment.CenterStart, Alignment.BottomEnd)
                            .padding(60.dp, 16.dp, 16.dp, 16.dp),
                        color = if (textSize.value >= 19) {
                            Color.Transparent
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        },
                        fontSize = textSize
                    )
                }

                IconButton(onClick = {
                    navigator.popBackStack()
                }, modifier = Modifier.clip(RoundedCornerShape(100.dp))) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .padding(10.dp)
                            .clip(RoundedCornerShape(100.dp))
                    )
                }


            }
        }
    ) {
        LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                ) {
                    if (collectionState.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            color = if (isSystemInDarkTheme()) Color.White else Color.Black
                        )
                    }
                }
            }
            item {
                collectionState.single?.let { it ->

                    if (textSize.value >= 19) {
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            it.name?.let { it1 ->
                                Text(
                                    modifier = Modifier.fillMaxWidth(0.85f),
                                    text = it1,
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                        }
                    }
                }
            }
            item {
                collectionState.single?.let { it ->
                    Column {
                        Spacer(modifier = Modifier.height(16.dp))
                        it.description?.let { it1 ->
                            Text(
                                text = it1,
                                modifier = Modifier.fillMaxWidth(),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                letterSpacing = 0.2.sp,
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        val context = LocalContext.current
                        val launcher =
                            rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {}
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.website),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(30.dp)
                                    .clickable(onClick = {
                                        val intent =
                                            Intent(
                                                Intent.ACTION_VIEW,
                                                Uri.parse(it.wiki_url.toString())
                                            )
                                        if (intent.resolveActivity(context.packageManager) != null) {
                                            launcher.launch(intent)
                                        }
                                    }),
                                tint = Color.Gray
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.discord),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(30.dp)
                                    .clickable(onClick = {
                                        val intent =
                                            Intent(Intent.ACTION_VIEW, Uri.parse(it.discord_url))
                                        if (intent.resolveActivity(context.packageManager) != null) {
                                            launcher.launch(intent)
                                        }
                                    }),
                                tint = Color.Gray
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.twitter),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(30.dp)
                                    .clickable(onClick = {
                                        val intent =
                                            Intent(
                                                Intent.ACTION_VIEW,
                                                Uri.parse(it.twitter_username)
                                            )
                                        if (intent.resolveActivity(context.packageManager) != null) {
                                            launcher.launch(intent)
                                        }
                                    }),
                                tint = Color.Gray
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        it.contracts?.take(1)?.forEach { met ->
                            val address = met.contract_address


                            val tabRowItems = listOf(
                                TabRowItem(
                                    title = "Items",
                                    screen = { ItemsScreen(address = address,navigator = navigator) },
                                    icon = R.drawable.baseline_equalizer_24,
                                ),
                                TabRowItem(
                                    title = "Activity",
                                    screen = { },
                                    icon = R.drawable.baseline_show_chart_24,
                                )
                            )
                            Debug(
                                pagerState = pagerState,
                                tabRowItems = tabRowItems,
                                coroutineScope = coroutineScope
                            )
                        }



                        Spacer(modifier = Modifier.height(20.dp))


                    }

                }

            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 0.6.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(10.dp)
            ),
        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(8.dp), // The TextFiled has rounded corners top left and right by default
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            cursorColor = Color.Black,
            leadingIconColor = Color.Black,
            trailingIconColor = Color.Black,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = "Search",
                style = MaterialTheme.typography.bodyMedium.merge(
                    TextStyle(
                        color = ListItemDefaults.contentColor.copy(
                            alpha = 0.5f
                        )
                    )
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    )
}
