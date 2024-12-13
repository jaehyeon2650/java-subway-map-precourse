package subway.service;

import java.util.List;
import subway.domain.Station;
import subway.repository.LineRepository;
import subway.repository.StationRepository;

public class StationService {
    private final LineRepository lineRepository;
    private final StationRepository stationRepository;

    public StationService(LineRepository lineRepository, StationRepository stationRepository) {
        this.lineRepository = lineRepository;
        this.stationRepository = stationRepository;
    }

    // 노선 기능
    public void makeLine(String line) {
        lineRepository.addLine(line);
    }

    public void addStationInLine(String lineName, String stationName) {
        Station findStation = stationRepository.getStation(stationName);
        lineRepository.addStation(lineName, findStation);
    }

    public List<String> getLines() {
        return lineRepository.getLinesNames();
    }

    public void deleteLine(String line) {
        lineRepository.deleteLineByName(line);
    }

    // 구간 기능
    public boolean hasLine(String lineName) {
        return lineRepository.hasLine(lineName);
    }

    public void insertStation(String lineName, String stationName, int index) {
        Station station = stationRepository.getStation(stationName);
        lineRepository.addStationAt(lineName, index - 1, station);
    }

    public void deleteStation(String line, String stationName) {
        Station station = stationRepository.getStation(stationName);
        lineRepository.deleteStation(line, station);
    }
    // 역 기능
}