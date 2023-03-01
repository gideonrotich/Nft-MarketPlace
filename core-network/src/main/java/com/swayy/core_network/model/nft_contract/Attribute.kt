package com.swayy.core_network.model.nft_contract

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class Attribute(
    val display_type: String,
    val max_value: String,
    val order: String,
    val trait_count: Int,
    val trait_type: String,
    val value: String
)