package com.swayy.home.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter

@Composable
fun HomeScreen(
    viewModel: ContractViewModel = hiltViewModel()
) {
    val constractState = viewModel.contracts.value

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(constractState.contracts) { matches ->
                    Column {

                        val ipfsLink = matches.normalized_metadata!!.image
                        val httpsLink = convertIpfsToHttps(ipfsLink!!)

                        val image: Painter =
                            rememberImagePainter(data = httpsLink)

                        Image(
                            modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            painter = image,
                            alignment = Alignment.Center,
                            contentDescription = "",
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(text = matches.normalized_metadata!!.name!!)
                    }


                }
            }
            if (constractState.error.isNotBlank()) {
                Text(
                    text = constractState.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)

                )
            }
            if (constractState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color.Blue

                )
            }
        }
    }
}

fun convertIpfsToHttps(ipfsLink: String): String {
    val gatewayUrl = "https://cloudflare-ipfs.com/ipfs/"
    val hash = ipfsLink.substringAfter("://")
    return "$gatewayUrl$hash"
}