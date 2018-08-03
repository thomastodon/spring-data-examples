package io.thomastodon.spring.data.examples.house;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

class Room {

    private final UUID uuid;
    private final List<Chair> chairs;

    Room(UUID uuid, List<Chair> chairs) {
        this.uuid = uuid;
        this.chairs = chairs;
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<Chair> getChairs() {
        return chairs;
    }

    @Override
    public String toString() {
        return "Room{" +
            "uuid=" + uuid +
            ", chairs=" + chairs +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(uuid, room.uuid) &&
            Objects.equals(chairs, room.chairs);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uuid, chairs);
    }
}
