package io.thomastodon.spring.data.examples.house;

import java.util.Objects;
import java.util.UUID;

class Leg {

    private final UUID uuid;

    Leg(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "Leg{" +
            "uuid=" + uuid +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Leg leg = (Leg) o;
        return Objects.equals(uuid, leg.uuid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uuid);
    }
}
