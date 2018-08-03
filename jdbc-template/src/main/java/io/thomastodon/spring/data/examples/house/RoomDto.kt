package io.thomastodon.spring.data.examples.house

import java.util.*

data class RoomDto(
    val uuid: UUID,
    val chairs: MutableList<ChairDto> = mutableListOf()
)