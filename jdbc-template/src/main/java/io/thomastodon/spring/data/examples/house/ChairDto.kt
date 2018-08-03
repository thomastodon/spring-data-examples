package io.thomastodon.spring.data.examples.house

import java.util.*

data class ChairDto(
    val legs: MutableMap<UUID, LegDto> = mutableMapOf()
)
