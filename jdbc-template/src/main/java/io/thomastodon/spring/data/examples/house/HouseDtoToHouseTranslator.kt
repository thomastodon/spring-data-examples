package io.thomastodon.spring.data.examples.house

class HouseDtoToHouseTranslator : Translator<HouseDto, House> {

    override fun translate(source: HouseDto): House {

        return House(
            source.uuid,
            source.rooms.map{ roomDto ->
                Room(
                    roomDto.uuid,
                    roomDto.chairs.map{ chairDto ->
                        Chair(
                            chairDto.uuid,
                            chairDto.legs.map{ legDto -> Leg(legDto.uuid) }
                        )
                    }
                )
            }
        )
    }
}
