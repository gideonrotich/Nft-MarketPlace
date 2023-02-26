package com.swayy.home.data

import com.swayy.core_database.model.ContractEntity
import com.swayy.core_network.model.nft_contract.NftContractResponse
import com.swayy.home.domain.model.Contract

internal fun ContractEntity.toContract(): Contract {
    return Contract(
        amount = amount,
        block_number_minted = block_number_minted,
        contract_type = contract_type,
        last_metadata_sync = last_metadata_sync,
        last_token_uri_sync = last_token_uri_sync,
        metadata = metadata,
        minter_address = minter_address,
        name = name,
        normalized_metadata = normalized_metadata,
        symbol = symbol,
        token_address = token_address,
        token_hash = token_hash,
        token_id = token_id,
        token_uri = token_uri,
        updated_at = updated_at
    )
}

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