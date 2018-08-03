package io.thomastodon.spring.data.examples.house;

import static java.util.stream.Collectors.toList;

public class HouseDtoToHouseTranslator implements Translator<HouseDto, House> {

    @Override
    public House translate(HouseDto source) {

        return new House(
            source.getUuid(),
            source.getRooms().stream().map(roomDto -> new Room(
                roomDto.getUuid(),
                roomDto.getChairs().stream().map(chairDto -> new Chair(
                    chairDto.getUuid(),
                    chairDto.getLegs().stream().map(legDto -> new Leg(legDto.getUuid())).collect(toList())
                )).collect(toList())
            )).collect(toList())
        );
    }
}
