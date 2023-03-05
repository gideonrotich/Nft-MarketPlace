package com.swayy.home.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.swayy.home.R

@Composable
fun TopBar() {
    Box(
        modifier = Modifier
            .height(50.dp)
            .fillMaxSize()
    ) {

        Column(modifier = Modifier.align(Alignment.Center)) {
            val isDarkTheme = isSystemInDarkTheme()

            if (isDarkTheme) {
                Image(
                    painter = painterResource(id = R.drawable.log),
                    contentDescription = "",
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .height(30.dp)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.gobbler),
                    contentDescription = "",
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .height(30.dp)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))
            Divider(color = Color.LightGray, thickness = 0.5.dp)
        }

    }
}