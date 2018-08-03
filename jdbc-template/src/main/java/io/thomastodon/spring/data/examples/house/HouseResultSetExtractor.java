package io.thomastodon.spring.data.examples.house;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HouseResultSetExtractor implements ResultSetExtractor<List<HouseDto>> {

    @Override
    public List<HouseDto> extractData(ResultSet rs) throws DataAccessException, SQLException {

        List<HouseDto> houses = new ArrayList<>();

        while (rs.next()) {

            UUID house_uuid = UUID.fromString(rs.getString("house_uuid"));
            if (houses.size() == 0 || !houses.get(0).getUuid().equals(house_uuid)) {
                houses.add(0, new HouseDto(house_uuid));
            }

            String roomUuidString = rs.getString("room_uuid");
            if (roomUuidString == null) continue;
            List<RoomDto> rooms = houses.get(0).getRooms();
            if (rooms.size() == 0 || !rooms.get(0).getUuid().equals(UUID.fromString(roomUuidString))) {
                RoomDto room = new RoomDto(UUID.fromString(roomUuidString));
                houses.get(0).getRooms().add(0, room);
            }

            String chairUuidString = rs.getString("chair_uuid");
            if (chairUuidString == null) continue;
            List<ChairDto> chairs = rooms.get(0).getChairs();
            if (chairs.size() == 0 || !chairs.get(0).getUuid().equals(UUID.fromString(chairUuidString))) {
                ChairDto chair = new ChairDto(UUID.fromString(chairUuidString));
                houses.get(0).getRooms().get(0).getChairs().add(0, chair);
            }

            String legUuidString = rs.getString("leg_uuid");
            if (legUuidString == null) continue;
            List<LegDto> legs = chairs.get(0).getLegs();
            if (legs.size() == 0 || !legs.get(0).getUuid().equals(UUID.fromString(legUuidString))) {
                LegDto leg = new LegDto(UUID.fromString(legUuidString));
                houses.get(0).getRooms().get(0).getChairs().get(0).getLegs().add(0, leg);
            }
        }

        return houses;
    }
}
