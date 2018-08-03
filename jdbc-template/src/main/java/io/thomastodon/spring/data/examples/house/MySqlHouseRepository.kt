package io.thomastodon.spring.data.examples.house

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.util.*

class MySqlHouseRepository(private val jdbcTemplate: NamedParameterJdbcTemplate) : HouseRepository {

    private val houseResultSetExtractor = HouseResultSetExtractor()
    private val houseDtoToHouseTranslator = HouseDtoToHouseTranslator()

    override fun findAll(): List<House> {

        val sql = ("SELECT"
            + " house.uuid AS house_uuid,"
            + " room.uuid AS room_uuid,"
            + " chair.uuid AS chair_uuid,"
            + " leg.uuid AS leg_uuid"
            + " FROM house"
            + " LEFT JOIN room ON house.uuid=room.house_uuid"
            + " LEFT JOIN chair ON room.uuid=chair.room_uuid"
            + " LEFT JOIN leg ON chair.uuid=leg.chair_uuid")

        val parameters = HashMap<String, Any>()

        return jdbcTemplate.query(sql, parameters, houseResultSetExtractor)
            .map { (houseUuid, houseDto) -> houseDtoToHouseTranslator.translate(houseUuid, houseDto) }
    }

    override fun saveHouse(house: House) {

        val sql = "INSERT INTO house (uuid) VALUES (:uuid)"

        val parameters = HashMap<String, Any>()
        parameters.put("uuid", house.uuid.toString())

        jdbcTemplate.update(sql, parameters)
    }

    override fun addRoomToHouse(room: Room, houseUuid: UUID) {

        val sql = "INSERT INTO room (uuid, house_uuid) VALUES (:uuid, :house_uuid)"

        val parameters = HashMap<String, Any>()
        parameters.put("uuid", room.uuid.toString())
        parameters.put("house_uuid", houseUuid.toString())

        jdbcTemplate.update(sql, parameters)
    }

    override fun addChairToRoom(chair: Chair, roomUuid: UUID) {

        val sql = "INSERT INTO chair (uuid, room_uuid) VALUES (:uuid, :room_uuid)"

        val parameters = HashMap<String, Any>()
        parameters.put("uuid", chair.uuid.toString())
        parameters.put("room_uuid", roomUuid.toString())

        jdbcTemplate.update(sql, parameters)
    }

    override fun addLegToChair(leg: Leg, chairUuid: UUID) {

        val sql = "INSERT INTO leg (uuid, chair_uuid) VALUES (:uuid, :chair_uuid)"

        val parameters = HashMap<String, Any>()
        parameters.put("uuid", leg.uuid.toString())
        parameters.put("chair_uuid", chairUuid.toString())

        jdbcTemplate.update(sql, parameters)
    }

    override fun deleteAll() {
        jdbcTemplate.jdbcTemplate.execute("DELETE FROM house")
    }
}
