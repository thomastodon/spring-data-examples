package io.thomastodon.spring.data.examples.house

data class Leg(
    val material: Material
)

enum class Material {
    WOOD, STEEL
}
