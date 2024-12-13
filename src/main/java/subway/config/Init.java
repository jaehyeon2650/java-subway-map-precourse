package subway.config;

import java.util.ArrayList;
import java.util.List;
import subway.domain.Line;
import subway.domain.Station;
import subway.repository.LineRepository;
import subway.repository.StationRepository;

public class Init {
    public static void init(LineRepository lineRepository, StationRepository stationRepository) {
        List<Station> stations = makeStations();
        stations.forEach(stationRepository::addStation);
        List<Line> lines = makeLines(stations);
        lines.forEach(lineRepository::addLine);
    }

    private static List<Station> makeStations() {
        return new ArrayList<>(List.of(
                new Station("교대역"),
                new Station("강남역"),
                new Station("역삼역"),
                new Station("남부터미널역"),
                new Station("양재역"),
                new Station("양재시민의숲역"),
                new Station("매봉역")
        ));
    }

    public static List<Line> makeLines(List<Station> stations) {
        Line line1 = new Line("2호선");
        line1.addStation(stations.get(0));
        line1.addStation(stations.get(1));
        line1.addStation(stations.get(2));
        Line line2 = new Line("3호선");
        line2.addStation(stations.get(0));
        line2.addStation(stations.get(3));
        line2.addStation(stations.get(4));
        line2.addStation(stations.get(6));
        Line line3 = new Line("신분당선");
        line3.addStation(stations.get(1));
        line3.addStation(stations.get(4));
        line3.addStation(stations.get(5));
        return new ArrayList<>(List.of(line1, line2, line3));
    }
}
