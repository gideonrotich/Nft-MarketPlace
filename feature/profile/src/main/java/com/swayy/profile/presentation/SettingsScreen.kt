/*
 * Copyright 2022 Joel Kanyi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.swayy.profile.presentation

import android.content.Intent
import android.net.Uri
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.eneskayiklik.myapplication.component.stepList
import com.swayy.compose_ui.components.StandardToolbar
import com.kanyideveloper.settings.presentation.components.SettingCard
import com.kanyideveloper.settings.presentation.components.ThemesDialog
import com.ramcosta.composedestinations.annotation.Destination
import com.swayy.compose_ui.theme.PrimaryColor
import com.swayy.core.R
import com.swayy.core.util.UiEvents
import com.swayy.profile.presentation.Demo.Wallet.components.AddWallet
import com.swayy.profile.presentation.Wallet.ConnectWalletViewModel
import com.swayy.profile.presentation.Wallet.component.introductionText

interface SettingsNavigator {
    fun openAllergiesScreen()
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Destination
@Composable
fun SettingsScreen(
    navigator: SettingsNavigator,
    viewModel: ConnectWalletViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val walletAddress = viewModel.userWallet.collectAsState().value



    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "Profile",
                color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant,
                style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(14.dp))
            androidx.compose.material.Divider(color = Color.LightGray, thickness = 0.5.dp)

            if (walletAddress != "") {
                Text(
                    text = walletAddress,
                    style = MaterialTheme.typography.h6.copy(
                        fontSize = 10.sp,
                        color = PrimaryColor
                    ),
                    textAlign = TextAlign.Center, modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 5.dp)
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray.copy(alpha = .2F)),
                    contentPadding = PaddingValues(vertical = 40.dp, horizontal = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    introductionText()
                    stepList(isWalletConnected = false) {
                        viewModel.connectWallet(context)
                    }
                }
            }

        }

    }

}


//Column {
//        Spacer(modifier = Modifier.height(20.dp))
//        Box(
//            modifier = Modifier
//                .height(50.dp)
//                .fillMaxSize()
//        ) {
//
//            Column(modifier = Modifier.fillMaxSize()) {
//                Text(
//                    text = "Profile",
//                    color = MaterialTheme.colorScheme.onSurfaceVariant,
//                    style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
//                    fontSize = 20.sp,
//                    modifier = Modifier
//                        .align(Alignment.CenterHorizontally),
//                    fontWeight = FontWeight.Bold,
//                )
//
//                Spacer(modifier = Modifier.height(14.dp))
//                androidx.compose.material.Divider(color = Color.LightGray, thickness = 0.5.dp)
//            }
//
//        }
//        Spacer(modifier = Modifier.height(20.dp))
//        AddWallet()
//        Spacer(modifier = Modifier.height(46.dp))
//        Text(
//            text = "Log into your wallet",
//            color = MaterialTheme.colorScheme.onSurfaceVariant,
//            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
//            fontSize = 16.sp,
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally),
//            fontWeight = FontWeight.Bold,
//        )
//        Spacer(modifier = Modifier.height(20.dp))
//        Text(
//            text = "Connect to any WalletConnect supported wallet \n to securely store your digital goods and \n cryptocurrencies.",
//            color = Color.Gray,
//            style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
//            fontSize = 15.sp,
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally),
//            fontWeight = FontWeight.Normal,
//        )
//        Spacer(modifier = Modifier.height(50.dp))
//        Button(
//            onClick = { /*TODO*/ },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 17.dp, end = 17.dp)
//                .height(46.dp),
//            shape = RoundedCornerShape(10.dp),
//        ) {
//            Text(
//                text = "Connect wallet",
//                color = Color.White,
//                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Normal,
//            )
//        }
//        Spacer(modifier = Modifier.height(60.dp))
//        Row(modifier = Modifier.align(Alignment.CenterHorizontally)){
//            Text(
//                text = "Dont have a wallet yet?",
//                color = Color.Gray,
//                style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
//                fontSize = 15.sp,
//                fontWeight = FontWeight.Normal,
//            )
//            Text(
//                text = "Learn more",
//                color = PrimaryColor,
//                style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
//                fontSize = 15.sp,
//                fontWeight = FontWeight.Normal,
//            )
//        }
//
//
//    }
