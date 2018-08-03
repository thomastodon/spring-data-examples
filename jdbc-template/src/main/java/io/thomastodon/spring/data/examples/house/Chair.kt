package io.thomastodon.spring.data.examples.house

import java.util.*

data class Chair(
    val reclines: Boolean,
    val legs: Map<UUID, Leg> = emptyMap()
)
