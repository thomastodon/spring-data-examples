package io.thomastodon.spring.data.examples.house

import java.util.*

class HouseDtoToHouseTranslator {

    fun translate(houseUuid: UUID, houseDto: HouseDto): House {

        return House(
            houseUuid,
            houseDto.rooms.map { (roomUuid, roomDto) ->
                Room(
                    roomUuid,
                    roomDto.chairs.map { (chairUuid, chairDto) ->
                        Chair(
                            chairUuid,
                            chairDto.legs.map { (legUuid, legDto) -> Leg(legUuid) }
                        )
                    }
                )
            }
        )
    }
}
