package subway.controller;

import subway.service.StationService;
import subway.view.InputView;
import subway.view.OutputView;

public class StationController {
    private final OutputView outputView;
    private final InputView inputView;
    private final StationService stationService;

    public StationController(OutputView outputView, InputView inputView, StationService stationService) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.stationService = stationService;
    }
}
