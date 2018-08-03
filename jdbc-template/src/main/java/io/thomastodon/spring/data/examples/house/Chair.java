package io.thomastodon.spring.data.examples.house;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

class Chair {

    private final UUID uuid;
    private final List<Leg> legs;

    Chair(UUID uuid, List<Leg> legs) {
        this.uuid = uuid;
        this.legs = legs;
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<Leg> getLegs() {
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
        Chair chair = (Chair) o;
        return Objects.equals(uuid, chair.uuid) &&
            Objects.equals(legs, chair.legs);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uuid, legs);
    }
}
