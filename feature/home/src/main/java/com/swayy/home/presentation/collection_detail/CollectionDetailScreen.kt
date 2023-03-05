package com.swayy.home.presentation.collection_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.swayy.home.presentation.home.HomeNavigator

@Destination
@Composable
fun CollectionDetailScreen(
    collectionId: String,
    navigator: HomeNavigator,
    viewModel: CollectionDetailViewmodel = hiltViewModel()
){
    val originalString = collectionId
    val newString = originalString.removePrefix("/")

    LaunchedEffect(key1 = true) {
        viewModel.getCollectionDetails(collection = newString)
    }

    val collectionState = viewModel.state.value
    
    Box(modifier = Modifier.fillMaxSize()) {
        collectionState.collectiondetail?.let { collection ->
            Column() {
                Text(text = collection.collection,
                    color = Color.Black)
                Text(text = collection.twitter,
                    color = Color.Black)
            }

        }
    }


}