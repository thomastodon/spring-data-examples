package io.thomastodon.spring.data.examples.house

import java.util.UUID

data class House(
    val uuid: UUID,
    val rooms: List<Room>
)