package subway.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import subway.domain.Station;
import subway.exception.ErrorMessage;
import subway.exception.StationException;

public class StationRepository {
    private static final List<Station> stations = new ArrayList<>();

    public List<String> getStationsNames() {
        return stations.stream().map(Station::getName).collect(Collectors.toList());
    }

    public void addStation(String name) {
        boolean hasStation = stations.stream().anyMatch(station -> {
            return station.getName().equals(name);
        });
        if (hasStation) {
            throw StationException.from(ErrorMessage.ALREADY_EXIST_STATION);
        }
        stations.add(new Station(name));
    }

    public void deleteStation(String name) {
        if (!stations.removeIf(station -> Objects.equals(station.getName(), name))) {
            throw StationException.from(ErrorMessage.NO_STATION);
        }
    }

}
