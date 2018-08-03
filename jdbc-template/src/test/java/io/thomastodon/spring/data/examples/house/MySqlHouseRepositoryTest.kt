package io.thomastodon.spring.data.examples.house

import io.thomastodon.spring.data.examples.Utility
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.UUID

import java.util.Collections.emptyList
import org.assertj.core.api.Assertions.assertThat

internal class MySqlHouseRepositoryTest {

    private lateinit var repository: MySqlHouseRepository

    @BeforeEach
    fun setUp() {
        repository = MySqlHouseRepository(Utility.jdbcTemplate)
    }

    @Test
    @DisplayName("findAll, returns all of the houses")
    fun test() {

        val houseOne = House(UUID.randomUUID(), emptyList())
        val houseTwo = House(UUID.randomUUID(), emptyList())
        val roomOne = Room(UUID.randomUUID(), emptyList())
        val roomTwo = Room(UUID.randomUUID(), emptyList())
        val chairOne = Chair(UUID.randomUUID(), emptyList())
        val chairTwo = Chair(UUID.randomUUID(), emptyList())
        val legOne = Leg(UUID.randomUUID())
        val legTwo = Leg(UUID.randomUUID())

        repository.saveHouse(houseOne)
        repository.saveHouse(houseTwo)
        repository.addRoomToHouse(roomOne, houseOne.uuid)
        repository.addRoomToHouse(roomTwo, houseOne.uuid)
        repository.addChairToRoom(chairOne, roomOne.uuid)
        repository.addChairToRoom(chairTwo, roomOne.uuid)
        repository.addLegToChair(legOne, chairOne.uuid)
        repository.addLegToChair(legTwo, chairOne.uuid)

        val houses = repository.findAll()

        assertThat(houses.size).isEqualTo(2)

        val actualHouseOne = houses.find { house -> house.uuid == houseOne.uuid }!!
        assertThat(actualHouseOne.rooms.size).isEqualTo(2)

        val actualHouseTwo = houses.find { house -> house.uuid == houseTwo.uuid }!!
        assertThat(actualHouseTwo).isEqualTo(House(houseTwo.uuid, emptyList()))

        val actualRoomOne = actualHouseOne.rooms.find { room -> room.uuid == roomOne.uuid }!!
        assertThat(actualRoomOne.chairs.size).isEqualTo(2)

        val actualRoomTwo = actualHouseOne.rooms.find { room -> room.uuid == roomTwo.uuid }!!
        assertThat(actualRoomTwo).isEqualTo(Room(roomTwo.uuid, emptyList()))

        val actualChairOne = actualRoomOne.chairs.find { chair -> chair.uuid == chairOne.uuid }!!
        assertThat(actualChairOne.legs.size).isEqualTo(2)

        val actualChairTwo = actualRoomOne.chairs.find { chair -> chair.uuid == chairTwo.uuid }!!
        assertThat(actualChairTwo).isEqualTo(Chair(chairTwo.uuid, emptyList()))

        assertThat(actualChairOne.legs).containsExactlyInAnyOrder(
            Leg(legOne.uuid),
            Leg(legTwo.uuid)
        )
    }

    @AfterEach
    fun tearDown() {
        repository.deleteAll()
    }
}