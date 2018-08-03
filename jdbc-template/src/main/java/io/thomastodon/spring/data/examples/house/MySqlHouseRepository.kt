package io.thomastodon.spring.data.examples.house

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.util.*

class MySqlHouseRepository(private val jdbcTemplate: NamedParameterJdbcTemplate) : HouseRepository {

    private val houseResultSetExtractor = HouseResultSetExtractor()
    private val houseDtoToHouseTranslator = HouseDtoToHouseTranslator()

    override fun findAll(): Map<UUID, House> {

        val sql = ("SELECT"
            + " house.uuid AS house_uuid,"
            + " house.style AS house_style,"
            + " room.uuid AS room_uuid,"
            + " room.square_footage AS room_square_footage,"
            + " chair.uuid AS chair_uuid,"
            + " chair.reclines AS chair_reclines,"
            + " leg.uuid AS leg_uuid,"
            + " leg.material AS leg_material"
            + " FROM house"
            + " LEFT JOIN room ON house.uuid=room.house_uuid"
            + " LEFT JOIN chair ON room.uuid=chair.room_uuid"
            + " LEFT JOIN leg ON chair.uuid=leg.chair_uuid")

        val parameters = HashMap<String, Any>()

        return jdbcTemplate.query(sql, parameters, houseResultSetExtractor)
            .mapValues { houseDtoToHouseTranslator.translate(it.value) }
    }

    override fun saveHouse(house: House): UUID {

        val sql = "INSERT INTO house (uuid, style) VALUES (:uuid, :style)"

        val parameters = HashMap<String, Any>()
        val uuid = UUID.randomUUID()
        parameters.put("uuid", uuid.toString())
        parameters.put("style", house.style.toString())

        jdbcTemplate.update(sql, parameters)

        return uuid
    }

    override fun addRoomToHouse(houseUuid: UUID, room: Room): UUID {

        val sql = "INSERT INTO room (uuid, house_uuid, square_footage) VALUES (:uuid, :house_uuid, :square_footage)"

        val parameters = HashMap<String, Any>()
        val uuid = UUID.randomUUID()
        parameters.put("uuid", uuid.toString())
        parameters.put("house_uuid", houseUuid.toString())
        parameters.put("square_footage", room.squareFootage)

        jdbcTemplate.update(sql, parameters)

        return uuid
    }

    override fun addChairToRoom(roomUuid: UUID, chair: Chair): UUID {

        val sql = "INSERT INTO chair (uuid, room_uuid, reclines) VALUES (:uuid, :room_uuid, :reclines)"

        val parameters = HashMap<String, Any>()
        val uuid = UUID.randomUUID()
        parameters.put("uuid", uuid.toString())
        parameters.put("reclines", chair.reclines)
        parameters.put("room_uuid", roomUuid.toString())

        jdbcTemplate.update(sql, parameters)

        return uuid
    }

    override fun addLegToChair(chairUuid: UUID, leg: Leg): UUID {

        val sql = "INSERT INTO leg (uuid, chair_uuid, material) VALUES (:uuid, :chair_uuid, :material)"

        val parameters = HashMap<String, Any>()
        val uuid = UUID.randomUUID()
        parameters.put("uuid", uuid.toString())
        parameters.put("material", leg.material.toString())
        parameters.put("chair_uuid", chairUuid.toString())

        jdbcTemplate.update(sql, parameters)

        return uuid
    }

    override fun deleteAll() {
        jdbcTemplate.jdbcTemplate.execute("DELETE FROM house")
    }
}
