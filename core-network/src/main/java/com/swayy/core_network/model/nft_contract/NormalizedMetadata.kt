package com.swayy.core_network.model.nft_contract

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NormalizedMetadata(
    @SerializedName("animation_url")
    val animation_url: String,
    @SerializedName("attributes")
    val attributes: List<Attribute>,
    @SerializedName("description")
    val description: String,
    @SerializedName("external_link")
    val external_link: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
):Parcelable