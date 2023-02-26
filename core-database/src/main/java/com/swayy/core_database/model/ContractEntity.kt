package com.swayy.core_database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.swayy.core_network.model.nft_contract.NormalizedMetadata
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Contract_Table")
data class ContractEntity(
    val amount: String,
    val block_number_minted: String,
    val contract_type: String,
    val last_metadata_sync: String,
    val last_token_uri_sync: String,
    val metadata: String,
    val minter_address: String,
    val name: String,
    val normalized_metadata: NormalizedMetadata,
    val symbol: String,
    val token_address: String,
    val token_hash: String,
    val token_id: String,
    val token_uri: String,
    val updated_at: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
):Parcelable
