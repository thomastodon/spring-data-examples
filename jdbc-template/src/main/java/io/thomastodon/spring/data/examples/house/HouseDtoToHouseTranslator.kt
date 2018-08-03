package io.thomastodon.spring.data.examples.house

class HouseDtoToHouseTranslator {

    fun translate(houseDto: HouseDto): House {

        return House(
            style = houseDto.style,
            rooms = houseDto.rooms.mapValues {
                Room(
                    squareFootage = it.value.squareFootage,
                    chairs = it.value.chairs.mapValues {
                        Chair(
                            reclines = it.value.reclines,
                            legs = it.value.legs.mapValues {
                                Leg(it.value.material)
                            }
                        )
                    }
                )
            }
        )
    }
}
