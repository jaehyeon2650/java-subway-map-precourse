package subway.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import subway.exception.ErrorMessage;
import subway.exception.StationException;

public class Line {
    private static final int MIM_NAME_LENGTH = 2;
    private static final int MIM_STATION = 2;

    private String name;
    private final List<Station> stations = new ArrayList<>();

    public Line(String name) {
        Validator.validateNameLength(name);
        this.name = name;
    }

    public boolean hasStation(Station findStation) {
        return stations.stream().anyMatch(station -> station == findStation);
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
        Validator.validateIndex(stations, index);
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
            if (name.length() < MIM_NAME_LENGTH) {
                throw StationException.from(ErrorMessage.INVALID_LINE_NAME_LENGTH);
            }
        }

        public static void validateDuplicateStation(List<Station> stations, Station station) {
            if (stations.contains(station)) {
                throw StationException.from(ErrorMessage.INVALID_DUPLICATE_STATION_IN_LINE);
            }
        }

        public static void validateIndex(List<Station> stations, int index) {
            if (index < 0 || stations.size() < index) {
                throw StationException.from(ErrorMessage.INVALID_INDEX_STATION_IN_LINE);
            }
        }

        private static void validateHasStation(List<Station> stations, Station station) {
            if (!stations.contains(station)) {
                throw StationException.from(ErrorMessage.NO_STATION_IN_LINE);
            }
        }

        private static void canDelete(List<Station> stations) {
            if (stations.size() <= MIM_STATION) {
                throw StationException.from(ErrorMessage.CANT_REMOVE_STATION_IN_LINE);
            }
        }
    }
}
