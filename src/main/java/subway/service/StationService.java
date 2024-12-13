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
}
