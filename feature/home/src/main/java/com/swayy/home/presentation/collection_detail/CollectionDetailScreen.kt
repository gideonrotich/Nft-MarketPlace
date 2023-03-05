package com.swayy.home.presentation.collection_detail

import android.annotation.SuppressLint
import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ramcosta.composedestinations.annotation.Destination
import com.swayy.home.presentation.home.HomeNavigator
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Destination
@Composable
fun CollectionDetailScreen(
    collectionId: String,
    navigator: HomeNavigator,
    viewModel: CollectionDetailViewmodel = hiltViewModel()
) {
    val originalString = collectionId
    val newString = originalString.removePrefix("/")

    LaunchedEffect(key1 = true) {
        viewModel.getCollectionDetails(collection = newString)
    }

    val collectionState = viewModel.state.value

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
            collectionState.collectiondetail?.let { it ->

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                   ){

                    Image(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .fillMaxSize()
                            .padding(bottom = 50.dp)
                            .parallax(ratio = 0.5f)
                            .graphicsLayer {
                                alpha = if (textSize.value == 18f) 0f else 1f
                            },
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(data = it.logo)
                                .apply(block = fun ImageRequest.Builder.() {
                                    placeholder(com.swayy.core.R.drawable.placeholder)
                                }).build()
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )


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
                                .data(data = it.logo)
                                .apply(block = fun ImageRequest.Builder.() {
                                    placeholder(com.swayy.core.R.drawable.placeholder)
                                }).build()
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )

                }


                Text(
                    text = it.collection,
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

                IconButton(onClick = {
                    navigator.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    ) {
        LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)) {
            item {
                collectionState.collectiondetail?.let { it ->

                    if (textSize.value >= 19) {
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(0.85f),
                                text = it.collection,
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )

                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                androidx.compose.material3.Text(
                    text = "D/InputTransport: history resample interval is too short, cannot uses it to resample!!\n" +
                            "D/InputTransport: history resample interval is too short, cannot uses it to resample!!\n" +
                            "D/InputTransport: history resample interval is too short, cannot uses it to resample!!\n" +
                            "W/HiTouch_PressGestureDetector: Touch pointer move a lot. The moving distance of X is:3.1907349, limit is:40The moving distance of Y is:41.33496, limit is:40\n" +
                            "D/InputTransport: history resample interval is too short, cannot uses it to resample!!\n" +
                            "D/InputTransport: history resample interval is too short, cannot uses it to resample!!\n" +
                            "D/InputTransport: history resample interval is too short, cannot uses it to resample!!\n" +
                            "D/InputTransport: history resample interval is too short, cannot uses it to resample!!",
                    style = MaterialTheme.typography.titleLarge
                )
            }

        }
    }


}


