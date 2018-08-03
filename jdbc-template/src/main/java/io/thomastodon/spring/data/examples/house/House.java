package io.thomastodon.spring.data.examples.house;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

class House {

    private final UUID uuid;
    private final List<Room> rooms;

    House(UUID uuid, List<Room> rooms) {
        this.uuid = uuid;
        this.rooms = rooms;
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public String toString() {
        return "House{" +
            "uuid=" + uuid +
            ", rooms=" + rooms +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Objects.equals(uuid, house.uuid) &&
            Objects.equals(rooms, house.rooms);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uuid, rooms);
    }
}
