package com.swayy.core_network.model.nft_contract

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Attribute(
    @SerializedName("display_type")
    val display_type: String,
    @SerializedName("max_value")
    val max_value: String,
    @SerializedName("order")
    val order: String,
    @SerializedName("trait_count")
    val trait_count: Int,
    @SerializedName("trait_type")
    val trait_type: String,
    @SerializedName("value")
    val value: Int
):Parcelable