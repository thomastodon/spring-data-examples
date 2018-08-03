package io.thomastodon.spring.data.examples.house;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

class RoomDto {

    private final UUID uuid;
    private final List<ChairDto> chairs;

    RoomDto(UUID uuid) {
        this.uuid = uuid;
        this.chairs = new ArrayList<>();
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<ChairDto> getChairs() {
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
        RoomDto room = (RoomDto) o;
        return Objects.equals(uuid, room.uuid) &&
            Objects.equals(chairs, room.chairs);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uuid, chairs);
    }
}
