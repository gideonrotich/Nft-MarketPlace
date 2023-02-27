package com.swayy.home.presentation.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    viewModel: ContractViewModel = hiltViewModel()
) {
    val contractState = viewModel.contracts.value

    LazyColumn() {
        items(contractState.contracts) { data ->
            Text(text = data.normalized_metadata.name)
            Text(text = data.name)
        }
    }
}