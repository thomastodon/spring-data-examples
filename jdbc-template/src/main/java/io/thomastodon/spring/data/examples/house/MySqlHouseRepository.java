package io.thomastodon.spring.data.examples.house;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MySqlHouseRepository implements HouseRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private ResultSetExtractor<List<House>> houseResultSetExtractor = new HouseResultSetExtractor();

    MySqlHouseRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<House> findAll() {

        String sql = "SELECT"
            + " house.uuid AS house_uuid,"
            + " room.uuid AS room_uuid,"
            + " chair.uuid AS chair_uuid,"
            + " leg.uuid AS leg_uuid"
            + " FROM house"
            + " LEFT JOIN room ON house.uuid=room.house_uuid"
            + " LEFT JOIN chair ON room.uuid=chair.room_uuid"
            + " LEFT JOIN leg ON chair.uuid=leg.chair_uuid"
            + " ORDER BY house.uuid, room.uuid, chair.uuid";

        Map<String, Object> parameters = new HashMap<>();

        return jdbcTemplate.query(sql, parameters, houseResultSetExtractor);
    }

    @Override
    public void saveHouse(House house) {

        String sql = "INSERT INTO house (uuid) VALUES (:uuid)";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("uuid", house.getUuid().toString());

        jdbcTemplate.update(sql, parameters);
    }

    @Override
    public void addRoomToHouse(Room room, UUID houseUuid) {

        String sql = "INSERT INTO room (uuid, house_uuid) VALUES (:uuid, :house_uuid)";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("uuid", room.getUuid().toString());
        parameters.put("house_uuid", houseUuid.toString());

        jdbcTemplate.update(sql, parameters);
    }

    @Override
    public void addChairToRoom(Chair chair, UUID roomUuid) {

        String sql = "INSERT INTO chair (uuid, room_uuid) VALUES (:uuid, :room_uuid)";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("uuid", chair.getUuid().toString());
        parameters.put("room_uuid", roomUuid.toString());

        jdbcTemplate.update(sql, parameters);
    }

    @Override
    public void addLegToChair(Leg leg, UUID chairUuid) {

        String sql = "INSERT INTO leg (uuid, chair_uuid) VALUES (:uuid, :chair_uuid)";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("uuid", leg.getUuid().toString());
        parameters.put("chair_uuid", chairUuid.toString());

        jdbcTemplate.update(sql, parameters);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.getJdbcTemplate().execute("DELETE FROM house");
    }
}
