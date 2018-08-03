package io.thomastodon.spring.data.examples.house

import java.util.*

data class ChairDto(
    val uuid: UUID,
    val legs: MutableList<LegDto> = mutableListOf()
)
