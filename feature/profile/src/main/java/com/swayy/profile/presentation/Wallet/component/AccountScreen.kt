package com.swayy.profile.presentation.Wallet.component

import android.content.ClipData
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.swayy.core.R
import com.swayy.core.components.EmptyStateComponent
import com.swayy.core.util.LottieAnim
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AccountScreen(address: String) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column {

            Spacer(modifier = Modifier.height(60.dp))

            Image(
                painter = painterResource(id = R.drawable.test), contentDescription = "",
                modifier = Modifier
                    .size(120.dp)
                    .padding(start = 14.dp)
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "Unnamed",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(start = 20.dp),
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(7.dp))
            val inputString = address
            val visibleCharacters = 13
            val shortenedString = shortenHexString(inputString, visibleCharacters)

            val clipboardManager: ClipboardManager = LocalClipboardManager.current
            val coroutineScope = rememberCoroutineScope()
            val context = LocalContext.current

            Row(modifier = Modifier
                .clickable {
                    clipboardManager.setText(AnnotatedString((address)))
                    coroutineScope.launch {
                        Toast
                            .makeText(context, "Address copied!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                .padding(start = 20.dp, bottom = 10.dp)) {
                Text(
                    text = shortenedString,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp,
                    modifier = Modifier,
                    fontWeight = FontWeight.Normal,
                )
                Image(
                    painter = painterResource(id = R.drawable.dat),
                    contentDescription = "",
                    modifier = Modifier
                        .size(14.dp)
                        .padding(top = 2.5.dp),
                    colorFilter = ColorFilter.tint(Color.Gray)
                )
            }

            Spacer(Modifier.width(16.dp))

            val tabRowItems = listOf(
                TabRowItem(
                    title = "Collected",
                    screen = { TabScreen(text = "Tab 1") },
                    icon = R.drawable.baseline_grid_on_24,
                ),
                TabRowItem(
                    title = "Created",
                    screen = { TabScreen(text = "Tab 2") },
                    icon = R.drawable.baseline_format_paint_24,
                ),
                TabRowItem(
                    title = "Favorites",
                    screen = { TabScreen(text = "Tab 3") },
                    icon = R.drawable.baseline_favorite_border_24,
                )
            )

            val pagerState = rememberPagerState()

            Column(
                modifier = Modifier
                    .padding(0.dp)
            ) {
                TabRow(
                    selectedTabIndex = pagerState.currentPage,
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                            color = MaterialTheme.colorScheme.secondary
                        )
                    },
                    backgroundColor = Color.LightGray.copy(alpha = .0F),
                ) {
                    tabRowItems.forEachIndexed { index, item ->
                        Tab(
                            selected = pagerState.currentPage == index,
                            onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },

                            text = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Image(
                                        painter = painterResource(id = item.icon),
                                        contentDescription = "",
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(Modifier.width(4.dp))
                                    Text(
                                        text = item.title,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        )
                    }
                }
                HorizontalPager(
                    count = tabRowItems.size,
                    state = pagerState,
                ) {
                    tabRowItems[pagerState.currentPage].screen()
                }
            }

        }

    }
}

fun shortenHexString(inputString: String, visibleCharacters: Int): String {
    if (inputString.length <= visibleCharacters) {
        return inputString
    }

    val ellipsis = "..."
    val prefixLength = (visibleCharacters - ellipsis.length) / 2
    val suffixLength = visibleCharacters - ellipsis.length - prefixLength

    val prefix = inputString.substring(0, prefixLength)
    val suffix = inputString.substring(inputString.length - suffixLength)

    return "$prefix$ellipsis$suffix"
}

data class TabRowItem(
    val title: String,
    val icon: Int,
    val screen: @Composable () -> Unit,
)

@Composable
fun TabScreen(
    text: String,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = PaddingValues(16.dp)),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()) {
            val textState = remember { mutableStateOf(TextFieldValue("")) }
            SearchView(state = textState)
        }
        EmptyStateComponent()

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
                androidx.compose.material3.IconButton(
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
            androidx.compose.material.Text(
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



