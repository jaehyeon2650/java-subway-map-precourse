package subway.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import subway.domain.Line;
import subway.domain.Station;
import subway.exception.ErrorMessage;
import subway.exception.StationException;

public class LineRepository {
    private static final List<Line> lines = new ArrayList<>();

    public List<String> getLinesNames() {
        return lines.stream().map(Line::getName).collect(Collectors.toList());
    }

    public void deleteLineByName(String name) {
        if (!lines.removeIf(line -> Objects.equals(line.getName(), name))) {
            throw StationException.from(ErrorMessage.NO_LINE);
        }
    }

    public boolean hasLine(String line) {
        return lines.stream().anyMatch(next -> {
            return next.getName().equals(line);
        });
    }

    public void addLine(String line) {
        if (hasLine(line)) {
            throw StationException.from(ErrorMessage.ALREADY_EXIST_LINE);
        }
        lines.add(new Line(line));
    }

    public void addStation(String lineName, Station station) {
        Line findLine = lines.stream().filter(line -> {
            return line.getName().equals(lineName);
        }).findFirst().orElseThrow(() -> StationException.from(ErrorMessage.NO_LINE));
        findLine.addStation(station);
    }

    public void addStationAt(String lineName, int index, Station station) {
        Line findLine = lines.stream().filter(line -> {
            return line.getName().equals(lineName);
        }).findFirst().orElseThrow(() -> StationException.from(ErrorMessage.NO_LINE));
        findLine.addStationAt(station, index);
    }

    public List<String> getStation(String lineName) {
        Line findLine = lines.stream().filter(line -> {
            return line.getName().equals(lineName);
        }).findFirst().orElseThrow(() -> StationException.from(ErrorMessage.NO_LINE));
        return findLine.getStations();
    }

}
