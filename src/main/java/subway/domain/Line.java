package subway.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import subway.exception.ErrorMessage;
import subway.exception.StationException;

public class Line {
    private String name;
    private final List<Station> stations = new ArrayList<>();

    public Line(String name) {
        Validator.validateNameLength(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addStation(Station station) {
        Validator.validateDuplicateStation(stations, station);
        stations.add(station);
    }

    public void addStationAt(Station station, int index) {
        Validator.validateDuplicateStation(stations, station);
        stations.add(index, station);
    }

    public void deleteStation(Station station) {
        Validator.validateDelete(stations, station);
        stations.remove(station);
    }

    public List<String> getStations() {
        return stations.stream().map(Station::getName).collect(Collectors.toList());
    }

    private static class Validator {
        public static void validateDelete(List<Station> stations, Station station) {
            validateHasStation(stations, station);
            canDelete(stations);
        }

        public static void validateNameLength(String name) {
            if (name.length() < 2) {
                throw StationException.from(ErrorMessage.INVALID_LINE_NAME_LENGTH);
            }
        }

        public static void validateDuplicateStation(List<Station> stations, Station station) {
            if (stations.contains(station)) {
                throw StationException.from(ErrorMessage.INVALID_DUPLICATE_STATION_IN_LINE);
            }
        }

        private static void validateHasStation(List<Station> stations, Station station) {
            if (!stations.contains(station)) {
                throw StationException.from(ErrorMessage.NO_STATION_IN_LINE);
            }
        }

        private static void canDelete(List<Station> stations) {
            if (stations.size() <= 2) {
                throw StationException.from(ErrorMessage.CANT_REMOVE_STATION);
            }
        }
    }
}
