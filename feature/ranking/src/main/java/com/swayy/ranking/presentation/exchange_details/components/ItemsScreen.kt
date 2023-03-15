package com.swayy.ranking.presentation.exchange_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ramcosta.composedestinations.annotation.Destination
import com.swayy.core.R
import com.swayy.ranking.presentation.exchange_details.SearchView
import com.swayy.ranking.presentation.ranking.RankingNavigator
import com.swayy.ranking.presentation.ranking.RankingViewModel


@Composable
fun ItemsScreen(
    address: String,
    viewModel: RankingViewModel = hiltViewModel(),
    navigator: RankingNavigator
) {

    LaunchedEffect(key1 = true) {
        viewModel.getNft(contract_address = address, chain = "eth-main")
    }

    val collectionState = viewModel.nft.value

    Column(modifier = Modifier.fillMaxSize()) {
        val textState = remember { mutableStateOf(TextFieldValue("")) }
        Spacer(modifier = Modifier.height(20.dp))
        SearchView(state = textState)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally)
        ) {
            if (collectionState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 20.dp)
                        .size(28.dp),
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    strokeWidth = 2.6.dp
                )
            }
        }

        LazyVerticalGrid(columns = GridCells.Fixed(2),
            modifier = Modifier
                .height(500.dp)
                .padding(top = 20.dp)
                .width(500.dp), content = {
                items(collectionState.nft) { data ->
                    Box(
                        modifier = Modifier
                            .width(240.dp)
                            .height(230.dp)
                            .padding(6.dp)
                            .clickable(onClick = {
                                navigator.OpenNftDetails(
                                    contract_address = data.contract_address!!,
                                    token_id = data.id!!,
                                    chain = "eth-main"
                                )
                            })
                    ) {
                        Card(
                            modifier = Modifier.fillMaxSize(),
                            elevation = 1.dp,
                            shape = RoundedCornerShape(12.dp),
                            backgroundColor = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.scrim else Color.White
                        ) {
                            Column {
                                Image(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(150.dp),
                                    painter = rememberAsyncImagePainter(
                                        ImageRequest.Builder(LocalContext.current)
                                            .data(data = data.cached_images?.original)
                                            .apply(block = fun ImageRequest.Builder.() {
                                                placeholder(R.drawable.placeholder)
                                            }).build()
                                    ),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                data.token_name?.let {
                                    Text(
                                        text = it,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        style = MaterialTheme.typography.titleSmall,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(start = 10.dp)
                                    )
                                }
                            }
                        }

                    }
                }
            })


    }

}