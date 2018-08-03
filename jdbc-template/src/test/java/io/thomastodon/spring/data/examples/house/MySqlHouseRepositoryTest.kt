package io.thomastodon.spring.data.examples.house

import io.thomastodon.spring.data.examples.Utility
import io.thomastodon.spring.data.examples.house.Material.STEEL
import io.thomastodon.spring.data.examples.house.Material.WOOD
import io.thomastodon.spring.data.examples.house.Style.RANCH
import io.thomastodon.spring.data.examples.house.Style.THREE_DECKER
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MySqlHouseRepositoryTest {

    private lateinit var repository: MySqlHouseRepository

    @BeforeEach
    fun setUp() {
        repository = MySqlHouseRepository(Utility.jdbcTemplate)
    }

    @Test
    @DisplayName("findAll, returns all of the houses")
    fun test() {

        val houseOne = House(RANCH)
        val houseTwo = House(THREE_DECKER)
        val roomOne = Room(123)
        val roomTwo = Room(456)
        val chairOne = Chair(true)
        val chairTwo = Chair(false)
        val legOne = Leg(STEEL)
        val legTwo = Leg(WOOD)

        val houseOneUuid = repository.saveHouse(houseOne)
        val houseTwoUuid = repository.saveHouse(houseTwo)
        val roomOneUuid = repository.addRoomToHouse(houseOneUuid, roomOne)
        val roomTwoUuid = repository.addRoomToHouse(houseOneUuid, roomTwo)
        val chairOneUuid = repository.addChairToRoom(roomOneUuid, chairOne)
        val chairTwoUuid = repository.addChairToRoom(roomOneUuid, chairTwo)
        val legOneUuid = repository.addLegToChair(chairOneUuid, legOne)
        val legTwoUuid = repository.addLegToChair(chairOneUuid, legTwo)

        val houses = repository.findAll()

        val expectedHouseOne = House(
            style = RANCH,
            rooms = mapOf(
                roomOneUuid to Room(
                    squareFootage = 123,
                    chairs = mapOf(
                        chairOneUuid to Chair(
                            reclines = true,
                            legs = mapOf(legOneUuid to Leg(STEEL), legTwoUuid to Leg(WOOD))
                        ),
                        chairTwoUuid to Chair(reclines = false, legs = emptyMap())
                    )
                ),
                roomTwoUuid to Room(squareFootage = 456, chairs = emptyMap())
            )
        )

        val expectedHouseTwo = House(style = THREE_DECKER, rooms = emptyMap())

        assertThat(houses).isEqualTo(mapOf(houseOneUuid to expectedHouseOne, houseTwoUuid to expectedHouseTwo))
    }

    @AfterEach
    fun tearDown() {
        repository.deleteAll()
    }
}