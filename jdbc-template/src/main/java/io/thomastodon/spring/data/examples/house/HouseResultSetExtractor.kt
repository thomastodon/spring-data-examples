package io.thomastodon.spring.data.examples.house

import org.springframework.jdbc.core.ResultSetExtractor
import java.sql.ResultSet
import java.util.*

class HouseResultSetExtractor : ResultSetExtractor<Map<UUID, HouseDto>> {

    override fun extractData(rs: ResultSet): Map<UUID, HouseDto> {

        val houses = mutableMapOf<UUID, HouseDto>()

        while (rs.next()) {

            val houseUuid = UUID.fromString(rs.getString("house_uuid"))
            val house = houses.getOrPut(houseUuid, { HouseDto() })

            val roomUuidString = rs.getString("room_uuid") ?: continue
            val room = house.rooms.getOrPut(UUID.fromString(roomUuidString), { RoomDto() })

            val chairUuidString = rs.getString("chair_uuid") ?: continue
            val chair = room.chairs.getOrPut(UUID.fromString(chairUuidString), { ChairDto() })

            val legUuidString = rs.getString("leg_uuid") ?: continue
            chair.legs.put(UUID.fromString(legUuidString), LegDto("wood"))
        }

        return houses
    }
}
