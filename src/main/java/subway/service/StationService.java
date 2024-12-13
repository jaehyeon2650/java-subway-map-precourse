package subway.service;

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

    public List<String> getLines() {
        return lineRepository.getLinesNames();
    }

    public void deleteLine(String line) {
        lineRepository.deleteLineByName(line);
    }

    // 구간 기능
    public void addStationInLine(String lineName, String stationName) {
        Station findStation = stationRepository.getStation(stationName);
        lineRepository.addStation(lineName, findStation);
    }
    // 역 기능
}
