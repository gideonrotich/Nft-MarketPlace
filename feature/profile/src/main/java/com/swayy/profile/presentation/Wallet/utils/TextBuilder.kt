package com.eneskayiklik.myapplication.utils

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.swayy.compose_ui.theme.PrimaryColor


fun getCreateWalletText(
    index: Int,
) = when (index) {
    1 -> buildAnnotatedString {
        // First Step
        append("Click on the ")
        withStyle(SpanStyle(color = PrimaryColor)) {
            append("“Get Started”")
        }
        append(" button.")
    }
    2 -> buildAnnotatedString {
        // Second Step
        append("If you already have a account select ")
        withStyle(SpanStyle(color = PrimaryColor)) {
            append("“Import using Secret Recovery Phrase”")
        }
        append(" if you don't, select ")
        withStyle(SpanStyle(color = PrimaryColor)) {
            append("“Create a Wallet”")
        }
        append(" and after that create a ")
        withStyle(SpanStyle(color = PrimaryColor)) {
            append("“password”")
        }
    }
    3 -> buildAnnotatedString {
        append("Write down, store, or memorize your ")
        withStyle(SpanStyle(color = PrimaryColor)) {
            append("Secret Backup Phrase")
        }
    }
    4 -> buildAnnotatedString {
        withStyle(SpanStyle(color = PrimaryColor)) {
            append("Confirm your Secret Backup Phrase")
        }
        append(" to ensure you have it correct")
    }
    else -> buildAnnotatedString { }
}