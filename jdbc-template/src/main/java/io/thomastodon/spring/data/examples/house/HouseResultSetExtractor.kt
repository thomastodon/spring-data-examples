package io.thomastodon.spring.data.examples.house

import org.springframework.jdbc.core.ResultSetExtractor
import java.sql.ResultSet
import java.util.*

class HouseResultSetExtractor : ResultSetExtractor<List<HouseDto>> {

    override fun extractData(rs: ResultSet): List<HouseDto> {

        val houses = mutableListOf<HouseDto>()

        while (rs.next()) {

            val houseUuid = UUID.fromString(rs.getString("house_uuid"))
            if (houses.size == 0 || houses[0].uuid != houseUuid) {
                houses.add(0, HouseDto(houseUuid))
            }

            val roomUuidString = rs.getString("room_uuid") ?: continue
            val rooms = houses[0].rooms
            if (rooms.size == 0 || rooms[0].uuid != UUID.fromString(roomUuidString)) {
                val room = RoomDto(UUID.fromString(roomUuidString))
                rooms.add(0, room)
            }

            val chairUuidString = rs.getString("chair_uuid") ?: continue
            val chairs = rooms[0].chairs
            if (chairs.size == 0 || chairs[0].uuid != UUID.fromString(chairUuidString)) {
                val chair = ChairDto(UUID.fromString(chairUuidString))
                chairs.add(0, chair)
            }

            val legUuidString = rs.getString("leg_uuid") ?: continue
            val legs = chairs[0].legs
            if (legs.size == 0 || legs[0].uuid != UUID.fromString(legUuidString)) {
                val leg = LegDto(UUID.fromString(legUuidString))
                legs.add(0, leg)
            }
        }

        return houses
    }
}
