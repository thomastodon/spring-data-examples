package io.thomastodon.spring.data.examples.house;

import io.thomastodon.spring.data.examples.Utility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

class MySqlHouseRepositoryTest {

    private MySqlHouseRepository repository;

    @BeforeEach
    void setUp() {
        repository = new MySqlHouseRepository(Utility.jdbcTemplate);
    }

    @Test
    @DisplayName("findAll, returns all of the houses")
    void test() {

        Leg leg = new Leg(UUID.randomUUID());
        Chair chair = new Chair(UUID.randomUUID(), emptyList());
        Room room = new Room(UUID.randomUUID(), emptyList());
        House house = new House(UUID.randomUUID(), emptyList());

        repository.saveHouse(house);
        repository.addRoomToHouse(room, house.getUuid());
        repository.addChairToRoom(chair, room.getUuid());
        repository.addLegToChair(leg, chair.getUuid());

        List<House> houses = repository.findAll();

        House expectedHouse = new House(house.getUuid(), asList(
            new Room(room.getUuid(), asList(
                new Chair(chair.getUuid(), asList(
                    new Leg(leg.getUuid())
                ))
            ))
        ));

        assertThat(houses).containsExactly(expectedHouse);
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }
}