package com.swayy.home.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.swayy.home.presentation.home.components.TopBar

@Composable
fun HomeScreen(
    viewModel: ContractViewModel = hiltViewModel()
) {
    val collectionState = viewModel.collections.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            TopBar()
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Trending",
                color = Color.Black,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 22.dp),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))

            Box() {
                Row( modifier = Modifier
                    .horizontalScroll(rememberScrollState())) {
                    Row {
                        Spacer(modifier = Modifier.width(30.dp))
                        Text(
                            text = "COLLECTION",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.width(130.dp))
                        Text(
                            text = "VOLUME",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }



                }

                Divider(color = Color.LightGray.copy(alpha = 1F), thickness = 0.5.dp, modifier = Modifier.padding(start = 26.dp, end = 80.dp, top = 30.dp))
            }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .horizontalScroll(rememberScrollState())
                    .padding(top = 16.dp)
            ) {

                LazyColumn(modifier = Modifier.width(290.dp)) {
                    itemsIndexed(collectionState.collections.take(5)) { index, matches ->

                        Row {
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "${index + 1}",
                                color = Color.Black,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(CenterVertically)
                            )

                            Spacer(modifier = Modifier.width(14.dp))

                            val image: Painter =
                                rememberImagePainter(data = "https://api.howrare.is/" + matches.logo)
                            Image(
                                modifier = Modifier
                                    .height(60.dp)
                                    .width(60.dp)
                                    .clip(RoundedCornerShape(14.dp)),
                                painter = image,
                                alignment = Alignment.Center,
                                contentDescription = "",
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(10.dp))

                            Column(modifier = Modifier.align(CenterVertically)) {
                                Text(
                                    text = matches.name,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.width(130.dp),
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    maxLines = 1
                                )
                                Text(
                                    text = "Floor " + matches.floor_marketcap_pretty,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Gray,
                                    fontSize = 13.sp
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))

                            Column(modifier = Modifier.align(CenterVertically)) {
                                Text(
                                    text = matches.on_sale.toString(),
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.width(100.dp),
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    maxLines = 1
                                )
                                Text(
                                    text = matches.holders.toString(),
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Green,
                                    fontSize = 13.sp
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                    }
                }

                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    itemsIndexed(collectionState.collections.takeLast(5)) { index, matches ->
                        Row {
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "${index + 1}",
                                color = Color.Black,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(CenterVertically)
                            )

                            Spacer(modifier = Modifier.width(14.dp))

                            val image: Painter =
                                rememberImagePainter(data = "https://api.howrare.is/" + matches.logo)
                            Image(
                                modifier = Modifier
                                    .height(60.dp)
                                    .width(60.dp)
                                    .clip(RoundedCornerShape(14.dp)),
                                painter = image,
                                alignment = Alignment.Center,
                                contentDescription = "",
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(10.dp))

                            Column(modifier = Modifier.align(CenterVertically)) {
                                Text(
                                    text = matches.name,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.width(130.dp),
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    maxLines = 1
                                )
                                Text(
                                    text = "Floor " + matches.floor_marketcap_pretty,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Gray,
                                    fontSize = 13.sp
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))

                            Column(modifier = Modifier.align(CenterVertically)) {
                                Text(
                                    text = matches.on_sale.toString(),
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.width(100.dp),
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    maxLines = 1
                                )
                                Text(
                                    text = matches.holders.toString(),
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Green,
                                    fontSize = 13.sp
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                    }

                }

            }
            if (collectionState.error.isNotBlank()) {
                Text(
                    text = collectionState.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)

                )
            }
            if (collectionState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color.Blue

                )
            }
        }
    }
}

//fun convertIpfsToHttps(ipfsLink: String): String {
//    val gatewayUrl = "https://cloudflare-ipfs.com/ipfs/"
//    val hash = ipfsLink.substringAfter("://")
//    return "$gatewayUrl$hash"
//}

//val ipfsLink = matches.normalized_metadata!!.image
//val httpsLink = convertIpfsToHttps(ipfsLink!!)


//Spacer(modifier = Modifier.height(2.dp))
//Text(text = matches.name)