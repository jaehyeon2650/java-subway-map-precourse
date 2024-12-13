package subway.domain;

import java.util.Objects;
import subway.exception.ErrorMessage;
import subway.exception.StationException;

public class Station {
    private static final int MIN_NAME_LENGTH = 2;

    private String name;

    public Station(String name) {
        Validator.validateName(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Station station = (Station) o;
        return Objects.equals(name, station.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    private static class Validator {
        public static void validateName(String name) {
            if (name.length() < MIN_NAME_LENGTH) {
                throw StationException.from(ErrorMessage.INVALID_STATION_NAME_LENGTH);
            }
        }
    }
}
