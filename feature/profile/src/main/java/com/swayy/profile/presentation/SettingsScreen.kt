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
import androidx.compose.foundation.clickable
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
import com.swayy.profile.presentation.Wallet.component.AccountScreen
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
    val walletType = viewModel.walletType.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {
        Column {

            if (walletAddress != "0") {
                AccountScreen(address = walletAddress)
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 50.dp, horizontal = 15.dp),
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


