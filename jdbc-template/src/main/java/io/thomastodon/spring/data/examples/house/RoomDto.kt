package io.thomastodon.spring.data.examples.house

import java.util.*

data class RoomDto(
    val chairs: MutableMap<UUID, ChairDto> = mutableMapOf()
)