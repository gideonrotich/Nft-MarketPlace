package com.swayy.profile.presentation.Wallet.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.swayy.compose_ui.theme.PrimaryColor

@Composable
fun WalletConnectStep(
    step: Int,
    centerContent: @Composable RowScope.() -> Unit,
    isExpanded: Boolean = false,
    isExpandable: Boolean = false,
    extendedContent: @Composable () -> Unit = { },
    onContentClick: () -> Unit,
) {
    val rotation = animateFloatAsState(targetValue = if (isExpanded) -180F else 0F)
    Column(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(15.dp))
        .background(Color.White)
        .clickable { onContentClick() }
        .padding(15.dp)
        .animateContentSize()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            StepCircle(step = step, modifier = Modifier.size(30.dp))
            Spacer(modifier = Modifier.width(10.dp))
            centerContent()
            if (isExpandable) {
                Image(
                    painter = painterResource(id = com.swayy.core.R.drawable.ic_down),
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1F)
                        .padding(top = 7.dp)
                        .rotate(rotation.value)
                )
            }
        }
        if (isExpanded) extendedContent()
    }
}

@Composable
private fun StepCircle(
    step: Int,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.border(1.5.dp, PrimaryColor, CircleShape),
        contentAlignment = Alignment.Center) {
        Text(text = "$step",
            style = MaterialTheme.typography.h1.copy(color = PrimaryColor, fontSize = 18.sp))
    }
}

@Composable
fun ExtendedStepText(
    step: Int,
    description: AnnotatedString,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = "$step.",
            style = MaterialTheme.typography.subtitle2.copy(color = PrimaryColor, fontSize = 14.sp))
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = description,
            style = MaterialTheme.typography.subtitle2.copy(color = PrimaryColor,
                fontSize = 14.sp,
                lineHeight = 18.sp))
    }
}