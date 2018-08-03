package io.thomastodon.spring.data.examples.house;

import java.util.List;
import java.util.UUID;

public interface HouseRepository {

    List<House> findAll();

    void saveHouse(House house);

    void addRoomToHouse(Room room, UUID houseUuid);

    void addChairToRoom(Chair chair, UUID roomUuid);

    void addLegToChair(Leg leg, UUID chairUuid);

    void deleteAll();
}
