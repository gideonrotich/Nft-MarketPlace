package com.swayy.profile.presentation.Demo.Wallet.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.swayy.core.R

@Composable
fun AddWallet() {
    Box(modifier = Modifier.height(110.dp)) {
        Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(50.dp))

        Image(
            painter = painterResource(id = R.drawable.wallet), contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .size(80.dp)
                .align(Alignment.CenterHorizontally)
        )

        }
    }
}