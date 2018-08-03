package io.thomastodon.spring.data.examples.house

import java.util.*

data class RoomDto(
    val squareFootage: Long,
    val chairs: MutableMap<UUID, ChairDto> = mutableMapOf()
)