package io.thomastodon.spring.data.examples.house;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;

public class HouseResultSetExtractor implements ResultSetExtractor<List<House>> {

    @Override
    public List<House> extractData(ResultSet rs) throws DataAccessException, SQLException {

        List<House> houses = new ArrayList<>();

        while (rs.next()) {

            UUID leg_uuid = UUID.fromString(rs.getString("leg_uuid"));
            Leg leg = new Leg(leg_uuid);

            UUID chair_uuid = UUID.fromString(rs.getString("chair_uuid"));
            Chair chair = new Chair(chair_uuid, asList(leg));

            UUID room_uuid = UUID.fromString(rs.getString("room_uuid"));
            Room room = new Room(room_uuid, asList(chair));

            UUID house_uuid = UUID.fromString(rs.getString("house_uuid"));
            House house = new House(house_uuid, asList(room));

            houses.add(house);
        }

        return houses;
    }
}
