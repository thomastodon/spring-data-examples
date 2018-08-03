package io.thomastodon.spring.data.examples.house;

import java.util.Objects;
import java.util.UUID;

class LegDto {

    private final UUID uuid;

    LegDto(UUID uuid) {
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
        LegDto leg = (LegDto) o;
        return Objects.equals(uuid, leg.uuid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uuid);
    }
}
