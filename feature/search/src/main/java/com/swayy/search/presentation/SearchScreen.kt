package com.swayy.search.presentation

import android.content.Context
import android.os.Build
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.ramcosta.composedestinations.annotation.Destination
import com.swayy.core.R
import com.swayy.home.presentation.home.gifLoader
import com.swayy.ranking.presentation.ranking.RankingNavigator
import com.swayy.ranking.presentation.ranking.RankingViewModel

interface SearchNavigator {
    fun openSearch()

    fun popBackStack()

    fun openExchangeDetails(collectionId:String)


}

@Destination
@Composable
fun SearchScreen(
    navigator: SearchNavigator,
    viewModel: RankingViewModel = hiltViewModel()
) {

    val exchangeState = viewModel.exchange.value
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(14.dp))
            androidx.compose.material3.Text(
                text = "Search",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(20.dp))
            val textState = remember { mutableStateOf(TextFieldValue("")) }
            SearchView(state = textState)
            Spacer(modifier = Modifier.height(20.dp))
            Divider(color = Color.LightGray.copy(alpha = 0.6f), thickness = 0.5.dp)
            Spacer(modifier = Modifier.height(20.dp))
            androidx.compose.material3.Text(
                text = "Trending Collections",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 18.dp),
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                if (exchangeState.isLoading){
                    androidx.compose.material.CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterVertically).padding(top = 20.dp)
                            .size(28.dp),
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        strokeWidth = 2.6.dp
                    )
                }
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                content = {
                    items(exchangeState.exchange) { search ->
                        Box(
                            modifier = Modifier
                                .width(240.dp)
                                .height(186.dp)
                                .padding(start = 10.dp, end = 10.dp, top = 12.dp)
                                .clickable(onClick = {
                                    navigator.openExchangeDetails(
                                        search.key!!
                                    )
                                })
                        ) {

                            androidx.compose.material.Card(
                                modifier = Modifier
                                    .fillMaxSize(),
                                elevation = 2.dp,
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Column(modifier = Modifier.fillMaxSize()) {

                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(120.dp)
                                        ) {

                                            if (search.featured_image_url != "") {
                                                Image(
                                                    painter = rememberAsyncImagePainter(
                                                        ImageRequest.Builder(context)
                                                            .data(data = search.featured_image_url)
                                                            .apply(block = {
                                                                size(Size.ORIGINAL)
                                                            }).placeholder(R.drawable.placeholder).build(),
                                                        imageLoader = context.gifLoader()
                                                    ),
                                                    contentDescription = null,
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .height(100.dp),
                                                    contentScale = ContentScale.Crop
                                                )
                                            } else {
                                                Image(
                                                    painter = rememberAsyncImagePainter(
                                                        ImageRequest.Builder(context)
                                                            .data(data = search.banner_image_url)
                                                            .apply(block = {
                                                                size(Size.ORIGINAL)
                                                            }).placeholder(R.drawable.placeholder).build(),
                                                        imageLoader = context.gifLoader()
                                                    ),
                                                    contentDescription = null,
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .height(100.dp),
                                                    contentScale = ContentScale.Crop
                                                )
                                            }
                                            if (search.featured_image_url == "") {
                                                Image(
                                                    painter = rememberAsyncImagePainter(
                                                        ImageRequest.Builder(context)
                                                            .data(data = search.image_url)
                                                            .apply(block = {
                                                                size(Size.ORIGINAL)
                                                            }).placeholder(R.drawable.placeholder).build(),
                                                        imageLoader = context.gifLoader()
                                                    ),
                                                    contentDescription = null,
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .height(100.dp),
                                                    contentScale = ContentScale.Crop
                                                )
                                            }

                                            Image(
                                                painter = rememberAsyncImagePainter(
                                                    ImageRequest.Builder(context)
                                                        .data(data = search.image_url)
                                                        .apply(block = {
                                                            size(Size.ORIGINAL)
                                                        }).build(),
                                                    imageLoader = context.gifLoader()
                                                ),
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .width(56.dp)
                                                    .height(46.dp)
                                                    .padding(start = 10.dp)
                                                    .align(Alignment.BottomStart)
                                                    .border(
                                                        width = 4.dp,
                                                        color = Color.White,
                                                        shape = RoundedCornerShape(6.dp)
                                                    )
                                                    .clip(RoundedCornerShape(6.dp)),
                                                contentScale = ContentScale.Crop
                                            )

                                        }
                                        androidx.compose.material3.Text(
                                            text = search.name!!,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            style = MaterialTheme.typography.titleMedium,
                                            fontSize = 14.sp,
                                            modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                                            fontWeight = FontWeight.Bold,
                                            maxLines = 1
                                        )
                                    }

                                }

                            }
                        }
                    }
                })

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
            .padding(start = 20.dp, end = 20.dp)
            .border(
                width = 0.6.dp,
                color = Color.LightGray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(10.dp)
            ),
        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp),
                tint = Color.Gray.copy(alpha = 0.7f)
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
                text = "Search OpenSea",
                style = MaterialTheme.typography.bodyMedium.merge(
                    TextStyle(
                        color = ListItemDefaults.contentColor.copy(
                            alpha = 0.5f
                        )
                    )
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
            )
        }
    )
}
