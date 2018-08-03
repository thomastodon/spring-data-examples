package io.thomastodon.spring.data.examples.house

import java.util.*

interface HouseRepository {

    fun findAll(): List<House>

    fun saveHouse(house: House)

    fun addRoomToHouse(room: Room, houseUuid: UUID)

    fun addChairToRoom(chair: Chair, roomUuid: UUID)

    fun addLegToChair(leg: Leg, chairUuid: UUID)

    fun deleteAll()
}
