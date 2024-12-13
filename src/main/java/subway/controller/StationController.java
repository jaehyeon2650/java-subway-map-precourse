package subway.controller;

import java.util.function.Supplier;
import subway.constant.Choose;
import subway.constant.MainChoose;
import subway.exception.StationException;
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

    public void start() {
        MainChoose mainChoose;
        do {
            mainChoose = retryInvalidInput(this::getMainChoose);
            if (mainChoose == MainChoose.STATION) {
                retryInvalidInput(this::startStationFunction);
            }
            if (mainChoose == MainChoose.LINE) {
                retryInvalidInput(this::startLineFunction);
            }
            if (mainChoose == MainChoose.PANEL) {
                retryInvalidInput(this::startPanelFunction);
            }
            if (mainChoose == MainChoose.INFO) {
                outputView.printAllSubwayInfo(stationService.getStationAllInfo());
            }
        } while (mainChoose != MainChoose.EXIT);
    }

    private MainChoose getMainChoose() {
        return inputView.getMainChoose();
    }


    private void startStationFunction() {
        Choose stationChoose = inputView.getStationChoose();
        if (stationChoose == Choose.ADD) {
            String station = inputView.addStation();
            stationService.addStation(station);
            outputView.printSuccessMakeStation();
        }
        if (stationChoose == Choose.DELETE) {
            String station = inputView.deleteStation();
            stationService.deleteStation(station);
            outputView.printSuccessDeleteStation();
        }
        if (stationChoose == Choose.FIND) {
            outputView.printStation(stationService.getStationNames());
        }
    }

    private void startLineFunction() {
        Choose lineChoose = inputView.getLineChoose();
        if (lineChoose == Choose.ADD) {
            String line = inputView.addLine();
            stationService.makeLine(line);
            String startStation = inputView.addStartStation();
            stationService.addStationInLine(line, startStation);
            String endStation = inputView.addEndStation();
            stationService.addStationInLine(line, endStation);
            outputView.printSuccessMakeLine();
        }
        if (lineChoose == Choose.DELETE) {
            String line = inputView.deleteLine();
            stationService.deleteLine(line);
            outputView.printSuccessDeleteLine();
        }
        if (lineChoose == Choose.FIND) {
            outputView.printLine(stationService.getLines());
        }
    }

    private void startPanelFunction() {
        Choose panelChoose = inputView.getPanelChoose();
        if (panelChoose == Choose.ADD) {
            String line = inputView.getLineForPanel();
            String station = inputView.getStationForPanel();
            int index = inputView.getIndexForPanel();
            stationService.insertStation(line, station, index);
            outputView.printSuccessMakePanel();
        }
        if (panelChoose == Choose.DELETE) {
            String line = inputView.getLineForDeletePanel();
            String station = inputView.getStationForDeletePanel();
            stationService.deleteStation(line, station);
            outputView.printSuccessDeletePanel();
        }
    }

    private <T> T retryInvalidInput(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (StationException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private <T> void retryInvalidInput(Runnable runnable) {
        while (true) {
            try {
                runnable.run();
                return;
            } catch (StationException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
