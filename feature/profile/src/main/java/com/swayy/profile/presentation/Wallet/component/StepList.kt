package com.eneskayiklik.myapplication.component

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eneskayiklik.myapplication.utils.*
import com.swayy.compose_ui.theme.PrimaryColor
import com.swayy.core.R
import com.swayy.profile.presentation.Wallet.component.ExtendedStepText
import com.swayy.profile.presentation.Wallet.component.WalletConnectStep

fun LazyListScope.stepList(
    isWalletConnected: Boolean,
    onConnectClick: () -> Unit
) {
    item {
        DownloadStep()
    }

    item {
        CreateOrConnectAccount()
    }

    item {
        Spacer(modifier = Modifier.height(20.dp))
        ConnectButton(isWalletConnected, onConnectClick)
    }
}

@Composable
fun DownloadStep() {
    val context = LocalContext.current
    WalletConnectStep(step = 1, centerContent = {
        Column(
            verticalArrangement = Arrangement.spacedBy(0.dp),
            modifier = Modifier.padding(top = 7.dp)
        ) {
            Text(
                text = "Download the MetaMask Android app",
                style = MaterialTheme.typography.subtitle2.copy(
                    color = PrimaryColor,
                    lineHeight = 18.sp
                )
            )
            Image(
                painter = painterResource(id = R.drawable.ic_google_play),
                contentDescription = null,
                modifier = Modifier.height(44.dp),
                contentScale = ContentScale.Fit
            )
        }
    }) {
        val packageName = "io.metamask"
        try {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$packageName")
                )
            )
        } catch (e: Exception) {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                )
            )
        }
    }
}

@Composable
fun CreateOrConnectAccount() {
    var isExpanded by remember { mutableStateOf(false) }
    WalletConnectStep(step = 2, isExpandable = true, isExpanded = isExpanded, centerContent = {
        Text(
            text = "After download process finish you can create or connect your wallet with following below steps",
            style = MaterialTheme.typography.subtitle2.copy(
                color = PrimaryColor,
                lineHeight = 18.sp
            ),
            modifier = Modifier
                .weight(9F)
                .padding(top = 7.dp)
        )
    }, extendedContent = {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, top = 5.dp)
        ) {
            ExtendedStepText(
                step = 1,
                description = getCreateWalletText(1)
            )
            ExtendedStepText(
                step = 2,
                description = getCreateWalletText(2)
            )
            ExtendedStepText(
                step = 3,
                description = getCreateWalletText(3)
            )
            ExtendedStepText(
                step = 4,
                description = getCreateWalletText(4)
            )
        }
    }) {
        isExpanded = isExpanded.not()
    }
}

@Composable
fun ConnectButton(isWalletConnected: Boolean, onConnectClick: () -> Unit) {
    val text = if (isWalletConnected) "Update Wallet" else "Connect Wallet"
    Button(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(46.dp), onClick = onConnectClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryColor)
    ) {
        Text(
            text = text, color = Color.White,
            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
        )
    }
}