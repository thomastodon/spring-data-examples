package io.thomastodon.spring.data.examples.house

import java.util.UUID

data class House(
    val style: Style,
    val rooms: Map<UUID, Room> = emptyMap()
)

enum class Style {
    THREE_DECKER, RANCH
}