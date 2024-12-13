package subway.config;

import subway.controller.StationController;
import subway.repository.LineRepository;
import subway.repository.StationRepository;
import subway.service.StationService;
import subway.view.InputView;
import subway.view.OutputView;

public class Config {

    private OutputView outputView() {
        return new OutputView();
    }

    private InputView inputView() {
        return new InputView();
    }

    private LineRepository lineRepository() {
        return new LineRepository();
    }

    private StationRepository stationRepository() {
        return new StationRepository();
    }

    private StationService stationService() {
        LineRepository lineRepository = lineRepository();
        StationRepository stationRepository = stationRepository();
        Init.init(lineRepository, stationRepository);
        return new StationService(lineRepository, stationRepository);
    }

    public StationController stationController() {
        return new StationController(outputView(), inputView(), stationService());
    }
}
