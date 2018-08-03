package io.thomastodon.spring.data.examples.house

import java.util.*

data class Room(
    val squareFootage: Long,
    val chairs: Map<UUID, Chair> = emptyMap()
)