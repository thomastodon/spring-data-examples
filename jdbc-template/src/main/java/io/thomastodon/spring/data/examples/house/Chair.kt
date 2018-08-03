package io.thomastodon.spring.data.examples.house

import java.util.*

data class Chair(
    val uuid: UUID,
    val legs: List<Leg>
)
