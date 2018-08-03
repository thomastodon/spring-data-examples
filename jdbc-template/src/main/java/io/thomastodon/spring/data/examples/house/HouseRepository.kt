package io.thomastodon.spring.data.examples.house

import java.util.*

interface HouseRepository {

    fun findAll(): Map<UUID, House>

    fun saveHouse(house: House): UUID

    fun addRoomToHouse(houseUuid: UUID, room: Room): UUID

    fun addChairToRoom(roomUuid: UUID, chair: Chair): UUID

    fun addLegToChair(chairUuid: UUID, leg: Leg): UUID

    fun deleteAll()
}
