package subway.service;

import java.util.List;
import subway.domain.Station;
import subway.dto.StationAllInfo;
import subway.exception.ErrorMessage;
import subway.exception.StationException;
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
    public void makeLine(String line, String startStationName, String endStationName) {
        lineRepository.checkHasLine(line);
        Station startStation = stationRepository.getStation(startStationName);
        Station endStation = stationRepository.getStation(endStationName);
        lineRepository.addLine(line);
        lineRepository.addStation(line, startStation);
        lineRepository.addStation(line, endStation);
    }

    public List<String> getLines() {
        return lineRepository.getLinesNames();
    }

    public void deleteLine(String line) {
        lineRepository.deleteLineByName(line);
    }

    // 구간 기능
    public void insertStation(String lineName, String stationName, int index) {
        Station station = stationRepository.getStation(stationName);
        lineRepository.addStationAt(lineName, index - 1, station);
    }

    public void deleteStation(String line, String stationName) {
        Station station = stationRepository.getStation(stationName);
        lineRepository.deleteStation(line, station);
    }

    // 역 기능
    public void addStation(String stationName) {
        stationRepository.addStation(stationName);
    }

    public void deleteStation(String stationName) {
        Station station = stationRepository.getStation(stationName);
        boolean hasStation = lineRepository.checkStation(station);
        if (hasStation) {
            throw StationException.from(ErrorMessage.CANT_REMOVE_STATION);
        }
        stationRepository.deleteStation(stationName);
    }

    public List<String> getStationNames() {
        return stationRepository.getStationsNames();
    }

    // 지하철 노선도 출력
    public StationAllInfo getStationAllInfo() {
        return lineRepository.getStationAllInfo();
    }
}
