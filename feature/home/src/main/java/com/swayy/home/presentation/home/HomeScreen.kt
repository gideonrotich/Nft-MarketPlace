package com.swayy.home.presentation.home

import android.content.Context
import android.os.Build
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.ramcosta.composedestinations.annotation.Destination
import com.swayy.compose_ui.R
import com.swayy.home.domain.model.Contract
import com.swayy.home.presentation.home.components.TopBar

interface HomeNavigator {
    fun openHome()
    fun popBackStack()

    fun openCollectionDetails(collectionId: String)

}

@Destination
@Composable
fun HomeScreen(
    navigator: HomeNavigator,
    viewModel: ContractViewModel = hiltViewModel()
) {
    val collectionState = viewModel.collections.value
    val nftState = viewModel.contracts.value
    val context = LocalContext.current

    Column() {
        Spacer(modifier = Modifier.height(14.dp))
        TopBar()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                Spacer(modifier = Modifier.height(6.dp))

                FilterOptionsComponent()
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Spotlights",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 22.dp),
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow(
                    modifier = Modifier.padding(all = 14.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(nftState.contracts.take(10)) { nft ->
                        val ipfsLink = nft.normalized_metadata!!.image
                        val httpsLink = convertIpfsToHttps(ipfsLink)
                        val image: Painter = rememberImagePainter(data = httpsLink)

                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(context).data(data = httpsLink).apply(block = {
                                    size(Size.ORIGINAL)
                                }).build(), imageLoader = context.gifLoader()
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(160.dp)
                                .clip(RoundedCornerShape(20.dp))
                        )

                    }
                }

                var isTrendingSelected by remember { mutableStateOf(true) }

                Row {
                    Text(
                        text = "Trending",
                        style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                        color = if (isTrendingSelected) Color.Black else Color.LightGray,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(start = 22.dp)
                            .clickable(onClick = { isTrendingSelected = true }),
                        fontWeight = if (isTrendingSelected) FontWeight.Bold else FontWeight.Normal,
                    )
                    Text(
                        text = "Top",
                        style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                        color = if (!isTrendingSelected) Color.Black else Color.LightGray,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .clickable(onClick = { isTrendingSelected = false }),
                        fontWeight = if (!isTrendingSelected) FontWeight.Bold else FontWeight.Normal,
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Box() {
                    Row(
                        modifier = Modifier
                            .horizontalScroll(rememberScrollState())
                    ) {
                        Row {
                            Spacer(modifier = Modifier.width(30.dp))
                            Text(
                                text = "COLLECTION",
                                color = Color.Gray,
                                style = androidx.compose.material3.MaterialTheme.typography.titleSmall,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.width(150.dp))
                            Text(
                                text = "VOLUME",
                                style = androidx.compose.material3.MaterialTheme.typography.titleSmall,
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                        }

                    }

                    Divider(
                        color = Color.LightGray.copy(alpha = 1F),
                        thickness = 0.5.dp,
                        modifier = Modifier.padding(start = 26.dp, end = 70.dp, top = 30.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .horizontalScroll(rememberScrollState())
                        .padding(top = 16.dp, start = 10.dp)
                ) {

                    if (isTrendingSelected) {

                        LazyHorizontalGrid(
                            GridCells.Fixed(5), modifier = Modifier
                                .height(350.dp)
                                .width(400.dp), content = {
                                itemsIndexed(collectionState.collections.take(10)) { index, matches ->

                                    Row(modifier = Modifier.clickable(onClick = {
                                        navigator.openCollectionDetails(
                                            matches.url
                                        )
                                    })) {

                                        Spacer(modifier = Modifier.width(12.dp))
                                        Text(
                                            text = "${index + 1}",
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
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
                                                .clip(RoundedCornerShape(14.dp))
                                                .align(CenterVertically),
                                            painter = image,
                                            contentDescription = "",
                                            contentScale = ContentScale.Crop
                                        )
                                        Spacer(modifier = Modifier.width(14.dp))

                                        Column(modifier = Modifier.align(CenterVertically)) {
                                            Text(
                                                text = matches.name,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.width(130.dp),
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                fontSize = 14.sp,
                                                maxLines = 1
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = "Floor: " + matches.floor_marketcap_pretty,
                                                fontWeight = FontWeight.Normal,
                                                color = Color.Gray,
                                                fontSize = 12.sp
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(10.dp))

                                        Column(modifier = Modifier.align(CenterVertically)) {
                                            Text(
                                                text = matches.on_sale.toString(),
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                fontSize = 14.sp,
                                                maxLines = 1
                                            )
                                            Text(
                                                text = matches.holders.toString() + "%",
                                                fontWeight = FontWeight.Normal,
                                                color = Color.Green,
                                                fontSize = 13.sp
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(14.dp))
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))

                                }
                            }
                        )
                    } else {
                        val sortedItems =
                            collectionState.collections.sortedByDescending { it.floor_marketcap_pretty }
                                .take(10)
                        LazyHorizontalGrid(
                            GridCells.Fixed(5), modifier = Modifier
                                .height(350.dp)
                                .width(400.dp), content = {
                                itemsIndexed(sortedItems) { index, matches ->

                                    Row(modifier = Modifier.clickable(onClick = {
                                        navigator.openCollectionDetails(
                                            matches.url
                                        )
                                    })) {

                                        Spacer(modifier = Modifier.width(12.dp))
                                        Text(
                                            text = "${index + 1}",
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
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
                                                .clip(RoundedCornerShape(14.dp))
                                                .align(CenterVertically),
                                            painter = image,
                                            contentDescription = "",
                                            contentScale = ContentScale.Crop
                                        )
                                        Spacer(modifier = Modifier.width(14.dp))

                                        Column(modifier = Modifier.align(CenterVertically)) {
                                            Text(
                                                text = matches.name,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.width(130.dp),
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                fontSize = 14.sp,
                                                maxLines = 1
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = "Floor: " + matches.floor_marketcap_pretty,
                                                fontWeight = FontWeight.Normal,
                                                color = Color.Gray,
                                                fontSize = 12.sp
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(10.dp))

                                        Column(modifier = Modifier.align(CenterVertically)) {
                                            Text(
                                                text = matches.on_sale.toString(),
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                fontSize = 14.sp,
                                                maxLines = 1
                                            )
                                            Text(
                                                text = matches.holders.toString() + "%",
                                                fontWeight = FontWeight.Normal,
                                                color = Color.Green,
                                                fontSize = 13.sp
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(14.dp))
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))

                                }
                            }
                        )
                    }


                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Notable Collections",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 22.dp),
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(20.dp))
                LazyRow(
                    modifier = Modifier.padding(all = 14.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(nftState.contracts.takeLast(10)) { nft ->
                        val ipfsLink = nft.normalized_metadata!!.image
                        val httpsLink = convertIpfsToHttps(ipfsLink)
                        val image: Painter = rememberImagePainter(data = httpsLink)

                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(context).data(data = httpsLink).apply(block = {
                                    size(Size.ORIGINAL)
                                }).build(), imageLoader = context.gifLoader()
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(160.dp)
                                .clip(RoundedCornerShape(20.dp))
                        )

                    }
                }

            }

        }
        if (collectionState.error.isNotBlank()) {
            Text(
                text = collectionState.error,
                color = MaterialTheme.colorScheme.error,
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

fun convertIpfsToHttps(ipfsLink: String): String {
    val gatewayUrl = "https://cloudflare-ipfs.com/ipfs/"
    val hash = ipfsLink.substringAfter("://")
    return "$gatewayUrl$hash"
}


val FILTER_CONTENT_LIST = listOf(
    FilterContent(Color.White, Color.LightGray.copy(0.4f), "Art"),
    FilterContent(Color.Black, Color.LightGray.copy(0.4f), "Gaming"),
    FilterContent(Color.Black, Color.LightGray.copy(0.4f), "Memberships"),
    FilterContent(Color.Black, Color.LightGray.copy(0.4f), "PFPs"),
    FilterContent(Color.Black, Color.LightGray.copy(0.4f), "Photography")
)

@Composable
fun FilterOptionsComponent() {
    val filterOptions = FILTER_CONTENT_LIST
    LazyRow(
        Modifier.padding(top = 4.dp, start = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
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
    val chipBackground = filter.backgroundColor
    val filterText = filter.filterText

    Chip(
        onClick = { /*TODO*/ },
        colors = ChipDefaults.chipColors(
            contentColor = contentColor,
            backgroundColor = chipBackground
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(modifier = Modifier.height(30.dp)) {
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = filterText,
                modifier = Modifier.align(CenterVertically),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


data class FilterContent(
    val contentColor: Color,
    val backgroundColor: Color,
    val filterText: String
)

fun Context.gifLoader(): ImageLoader {
    return ImageLoader.Builder(this)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
}