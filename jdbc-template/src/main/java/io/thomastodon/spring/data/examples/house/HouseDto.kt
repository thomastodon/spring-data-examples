package io.thomastodon.spring.data.examples.house

import java.util.*

data class HouseDto(
    val style: Style,
    val rooms: MutableMap<UUID, RoomDto> = mutableMapOf()
)
