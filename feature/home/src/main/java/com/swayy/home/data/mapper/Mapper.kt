package com.swayy.home.data

import com.swayy.core_database.model.ContractEntity
import com.swayy.core_network.model.collections.Data
import com.swayy.core_network.model.nft_contract.NftContractResponse
import com.swayy.home.domain.model.Collection
import com.swayy.home.domain.model.Contract

internal fun com.swayy.core_network.model.nft_contract.Result.toData(): Contract {
    return Contract(
        amount,
        block_number_minted,
        contract_type,
        last_metadata_sync,
        last_token_uri_sync,
        metadata,
        minter_address,
        name,
        normalized_metadata,
        symbol,
        token_address,
        token_hash,
        token_id,
        token_uri,
        updated_at
    )
}

internal fun Data.toCollection(): com.swayy.home.domain.model.Collection {
    return Collection(
        floor,
        floor_marketcap,
        floor_marketcap_pretty,
        holders,
        items,
        logo,
        me_key,
        metadata_refresh_ts,
        name,
        official_rarity,
        on_sale,
        url
    )
}