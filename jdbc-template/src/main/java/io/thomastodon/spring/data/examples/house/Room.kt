package io.thomastodon.spring.data.examples.house

import java.util.*

data class Room(
    val uuid: UUID,
    val chairs: List<Chair>
)