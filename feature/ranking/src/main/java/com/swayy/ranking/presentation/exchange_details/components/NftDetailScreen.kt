package com.swayy.ranking.presentation.exchange_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ramcosta.composedestinations.annotation.Destination
import com.swayy.core.R
import com.swayy.ranking.presentation.ranking.RankingViewModel

@Destination
@Composable
fun NftDetailScreen(
    contract_address: String,
    viewModel: RankingViewModel = hiltViewModel(),
    token_id: String,
    chain: String
) {

    LaunchedEffect(key1 = true) {
        viewModel.getNftDetail(
            contract_address = contract_address,
            token_id = token_id,
            chain = chain
        )
    }

    val state = viewModel.nftDetail.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
            .clip(RoundedCornerShape(14.dp))
    ) {
        state.nftDetail.let { nft ->
            androidx.compose.material.Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(330.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .padding(start = 12.dp, end = 12.dp), elevation = 3.dp
            ) {
                Column()
                {
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(14.dp)),
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(data = nft?.metadata?.image)
                                .apply(block = fun ImageRequest.Builder.() {
                                    placeholder(R.drawable.placeholder)
                                }).build()
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }

            }

        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 20.dp)
                    .size(28.dp),
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                strokeWidth = 2.6.dp
            )
        }

    }

}