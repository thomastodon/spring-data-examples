package io.thomastodon.spring.data.examples.house

import java.util.*

data class ChairDto(
    val reclines: Boolean,
    val legs: MutableMap<UUID, LegDto> = mutableMapOf()
)
