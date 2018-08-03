package io.thomastodon.spring.data.examples.house;

import io.thomastodon.spring.data.examples.Utility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

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

        House houseOne = new House(UUID.randomUUID(), emptyList());
        House houseTwo = new House(UUID.randomUUID(), emptyList());
        Room roomOne = new Room(UUID.randomUUID(), emptyList());
        Room roomTwo = new Room(UUID.randomUUID(), emptyList());
        Chair chairOne = new Chair(UUID.randomUUID(), emptyList());
        Chair chairTwo = new Chair(UUID.randomUUID(), emptyList());
        Leg legOne = new Leg(UUID.randomUUID());
        Leg legTwo = new Leg(UUID.randomUUID());

        repository.saveHouse(houseOne);
        repository.saveHouse(houseTwo);
        repository.addRoomToHouse(roomOne, houseOne.getUuid());
        repository.addRoomToHouse(roomTwo, houseOne.getUuid());
        repository.addChairToRoom(chairOne, roomOne.getUuid());
        repository.addChairToRoom(chairTwo, roomOne.getUuid());
        repository.addLegToChair(legOne, chairOne.getUuid());
        repository.addLegToChair(legTwo, chairOne.getUuid());

        List<House> houses = repository.findAll();

        assertThat(houses.size()).isEqualTo(2);

        House actualHouseOne = houses
            .stream()
            .filter(house -> house.getUuid().equals(houseOne.getUuid()))
            .findFirst()
            .get();
        assertThat(actualHouseOne.getRooms().size()).isEqualTo(2);

        House actualHouseTwo = houses
            .stream()
            .filter(house -> house.getUuid().equals(houseTwo.getUuid()))
            .findFirst()
            .get();
        assertThat(actualHouseTwo).isEqualTo(new House(houseTwo.getUuid(), emptyList()));

        Room actualRoomOne = actualHouseOne.getRooms()
            .stream()
            .filter(room -> room.getUuid().equals(roomOne.getUuid()))
            .findFirst()
            .get();
        assertThat(actualRoomOne.getChairs().size()).isEqualTo(2);

        Room actualRoomTwo = actualHouseOne.getRooms()
            .stream()
            .filter(room -> room.getUuid().equals(roomTwo.getUuid()))
            .findFirst()
            .get();
        assertThat(actualRoomTwo).isEqualTo(new Room(roomTwo.getUuid(), emptyList()));

        Chair actualChairOne = actualRoomOne.getChairs()
            .stream()
            .filter(chair -> chair.getUuid().equals(chairOne.getUuid()))
            .findFirst()
            .get();
        assertThat(actualChairOne.getLegs().size()).isEqualTo(2);

        Chair actualChairTwo = actualRoomOne.getChairs()
            .stream()
            .filter(chair -> chair.getUuid().equals(chairTwo.getUuid()))
            .findFirst()
            .get();
        assertThat(actualChairTwo).isEqualTo(new Chair(chairTwo.getUuid(), emptyList()));

        assertThat(actualChairOne.getLegs()).containsExactlyInAnyOrder(
            new Leg(legOne.getUuid()),
            new Leg(legTwo.getUuid())
        );
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }
}