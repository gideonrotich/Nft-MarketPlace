package com.swayy.home.presentation.collection_detail

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ramcosta.composedestinations.annotation.Destination
import com.swayy.core.R
import com.swayy.home.presentation.home.HomeNavigator
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ExperimentalToolbarApi
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@OptIn(ExperimentalToolbarApi::class)
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

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                ) {

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
                                fontWeight = FontWeight.Bold
                            )

                        }
                    }

                }
            }
            item {
                collectionState.collectiondetail?.let { it ->
                    Column {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = it.description,
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            letterSpacing = 0.2.sp,
                        )
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
                                            Intent(Intent.ACTION_VIEW, Uri.parse(it.website))
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
                                            Intent(Intent.ACTION_VIEW, Uri.parse(it.discord))
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
                                            Intent(Intent.ACTION_VIEW, Uri.parse(it.twitter))
                                        if (intent.resolveActivity(context.packageManager) != null) {
                                            launcher.launch(intent)
                                        }
                                    }),
                                tint = Color.Gray
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        val textState = remember { mutableStateOf(TextFieldValue("")) }
                        SearchView(state = textState)
                        Spacer(modifier = Modifier.height(20.dp))

                    }

                }

            }
            collectionState.collectiondetail?.let {

                item {
                    LazyVerticalGrid(columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .height(500.dp)
                            .width(500.dp), content = {
                            items(it.items!!.take(20)) { data ->
                                Box(
                                    modifier = Modifier
                                        .width(240.dp)
                                        .height(230.dp)
                                        .padding(6.dp)
                                ) {
                                    Card(
                                        modifier = Modifier.fillMaxSize(),
                                        elevation = 1.dp,
                                        shape = RoundedCornerShape(12.dp),
                                        backgroundColor = if (isSystemInDarkTheme()) androidx.compose.material3.MaterialTheme.colorScheme.scrim else Color.White
                                    ) {
                                        Column {
                                            Image(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(150.dp),
                                                painter = rememberAsyncImagePainter(
                                                    ImageRequest.Builder(LocalContext.current)
                                                        .data(data = data.image)
                                                        .apply(block = fun ImageRequest.Builder.() {
                                                            placeholder(com.swayy.core.R.drawable.placeholder)
                                                        }).build()
                                                ),
                                                contentDescription = null,
                                                contentScale = ContentScale.Crop
                                            )
                                            Spacer(modifier = Modifier.height(6.dp))
                                            androidx.compose.material3.Text(
                                                text = data.name,
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
                        })
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
            backgroundColor = Color.LightGray.copy(alpha = 0.6f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = "Search",
                style = MaterialTheme.typography.bodyMedium.merge(
                    TextStyle(
                        color = contentColor.copy(
                            alpha = 0.5f
                        )
                    )
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    )
}



