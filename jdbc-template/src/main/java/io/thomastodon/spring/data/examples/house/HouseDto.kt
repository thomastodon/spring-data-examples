package io.thomastodon.spring.data.examples.house

import java.util.*

data class HouseDto(
    val uuid: UUID,
    val rooms: MutableList<RoomDto> = mutableListOf()
)
