package com.swayy.ranking.data.mapper

import com.swayy.core_network.model.exchange.Result
import com.swayy.core_network.model.exchange.single.SingleResponseDto
import com.swayy.ranking.domain.model.Exchange
import com.swayy.ranking.domain.model.Ranking
import com.swayy.ranking.domain.model.Single

internal fun Result.toExchange(): Exchange {
    return Exchange(
        banner_image_url,
        chat_url,
        contracts,
        description,
        discord_url,
        exchange,
        exchange_url,
        external_url,
        featured_image_url,
        image_url,
        instagram_username,
        key,
        large_image_url,
        name,
        stats,
        telegram_url,
        twitter_username,
        update_at,
        wiki_url
    )
}

internal fun com.swayy.core_network.model.exchange.ranking.Result.toRanking(): Ranking {
    return Ranking(
        contracts,
        description,
        key,
        name,
        one_day_average_price,
        one_day_sales,
        one_day_volume,
        seven_day_average_price,
        seven_day_sales,
        seven_day_volume,
        thirty_day_average_price,
        thirty_day_sales,
        thirty_day_volume,
        total_average_price,
        total_sales,
        total_volume,
        update_at
    )
}

internal fun SingleResponseDto.toSingle(): Single {
    return Single(
        banner_image_url,
        chat_url,
        contracts,
        description,
        discord_url,
        exchange,
        exchange_url,
        external_url,
        featured_image_url,
        image_url,
        instagram_username,
        key,
        large_image_url,
        name,
        stats,
        telegram_url,
        twitter_username,
        update_at,
        wiki_url
    )
}