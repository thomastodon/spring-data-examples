package io.thomastodon.spring.data.examples.house;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

class ChairDto {

    private final UUID uuid;
    private final List<LegDto> legs;

    ChairDto(UUID uuid) {
        this.uuid = uuid;
        this.legs = new ArrayList<>();
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<LegDto> getLegs() {
        return legs;
    }

    @Override
    public String toString() {
        return "Chair{" +
            "uuid=" + uuid +
            ", legs=" + legs +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChairDto chair = (ChairDto) o;
        return Objects.equals(uuid, chair.uuid) &&
            Objects.equals(legs, chair.legs);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uuid, legs);
    }
}
