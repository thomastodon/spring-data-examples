package io.thomastodon.spring.data.examples.house;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

class HouseDto {

    private final UUID uuid;
    private final List<RoomDto> rooms;

    HouseDto(UUID uuid) {
        this.uuid = uuid;
        this.rooms = new ArrayList<>();
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<RoomDto> getRooms() {
        return rooms;
    }

    @Override
    public String toString() {
        return "HouseDto{" +
            "uuid=" + uuid +
            ", rooms=" + rooms +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HouseDto houseDto = (HouseDto) o;
        return Objects.equals(uuid, houseDto.uuid) &&
            Objects.equals(rooms, houseDto.rooms);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uuid, rooms);
    }
}
