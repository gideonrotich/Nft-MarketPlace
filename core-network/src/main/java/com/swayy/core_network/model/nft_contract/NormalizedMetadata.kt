package com.swayy.core_network.model.nft_contract

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class NormalizedMetadata(
    val animation_url: String,
    val attributes: List<Attribute>,
    val description: String,
    val external_link: String,
    val image: String,
    val name: String
)