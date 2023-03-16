package com.swayy.ranking.presentation.exchange_details.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.ramcosta.composedestinations.annotation.Destination
import com.swayy.compose_ui.theme.PrimaryColor
import com.swayy.core.R
import com.swayy.home.presentation.home.gifLoader
import com.swayy.ranking.presentation.ranking.RankingViewModel
import okhttp3.internal.notify

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
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color.LightGray.copy(alpha = .1f))
    ) {
        state.nftDetail.let { nft ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp, end = 12.dp),
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(context)
                                .data(data = if (nft?.cached_images?.original == null) nft?.metadata?.image else nft.cached_images.original)
                                .apply(block = {
                                    size(Size.ORIGINAL)
                                }).placeholder(R.drawable.placeholder).build(),
                            imageLoader = context.gifLoader()
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(330.dp)
                            .clip(RoundedCornerShape(14.dp)),
                        contentScale = ContentScale.Crop
                    )

                }
                Spacer(modifier = Modifier.height(12.dp))
                nft?.token_name?.let {
                    androidx.compose.material.Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 13.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = PrimaryColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                nft?.token_description?.let {
                    androidx.compose.material.Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 13.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                nft?.token_type?.let {
                    androidx.compose.material.Text(
                        text = "Chain " + it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 13.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))

                Row(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
                    Column(
                        modifier = Modifier
                            .padding(6.dp)
                            .size(70.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_favorite_24),
                            contentDescription = "",
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            colorFilter = ColorFilter.tint(Color.Gray)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        androidx.compose.material.Text(
                            text = "43",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        androidx.compose.material.Text(
                            text = "favorites",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                        )
                    }

                    Column(
                        modifier = Modifier
                            .padding(6.dp)
                            .size(70.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_supervisor_account_24),
                            contentDescription = "",
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            colorFilter = ColorFilter.tint(Color.Gray)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        androidx.compose.material.Text(
                            text = "1",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        androidx.compose.material.Text(
                            text = "owners",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(6.dp)
                            .size(70.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_grid_view_24),
                            contentDescription = "",
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            colorFilter = ColorFilter.tint(Color.Gray)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        androidx.compose.material.Text(
                            text = nft?.metadata?.edition.toString(),
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        androidx.compose.material.Text(
                            text = "editions",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(6.dp)
                            .size(70.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_remove_red_eye_24),
                            contentDescription = "",
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            colorFilter = ColorFilter.tint(Color.Gray)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        androidx.compose.material.Text(
                            text = "3",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        androidx.compose.material.Text(
                            text = "visitors",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp)
                ) {

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp)
                            .padding(bottom = 100.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                0.6.dp,
                                Color.LightGray.copy(alpha = .6f),
                                RoundedCornerShape(8.dp)
                            )
                    ) {
                        nft?.metadata?.let {
                            items(it.attributes) { dat ->
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    androidx.compose.material.Text(
                                        text = dat.trait_type,
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                            .padding(12.dp),
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 17.sp,
                                    )
                                    androidx.compose.material.Text(
                                        text = dat.value,
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                            .padding(6.dp),
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = PrimaryColor,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 15.sp,
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Divider(
                                        modifier = Modifier.fillMaxWidth(),
                                        thickness = 0.8.dp,
                                        color = Color.LightGray.copy(alpha = .6f)
                                    )
                                }

                            }
                        }
                    }
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .align(Alignment.BottomStart)
                .background(Color.White)
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                Button(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                        .padding(start = 20.dp, end = 20.dp)
                        .height(50.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryColor),
                    onClick = {}
                ) {
                    androidx.compose.material.Text(
                        text = "Buy", color = Color.White,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                    )
                }
            }


        }

    }

}