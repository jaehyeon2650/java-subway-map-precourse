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
            doFunction(mainChoose);
        } while (mainChoose != MainChoose.EXIT);
    }

    private MainChoose getMainChoose() {
        return inputView.getMainChoose();
    }

    private void doFunction(MainChoose mainChoose) {
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
    }


    private void startStationFunction() {
        Choose stationChoose = inputView.getStationChoose();
        if (stationChoose == Choose.ADD) {
            addStation();
        }
        if (stationChoose == Choose.DELETE) {
            deleteStation();
        }
        if (stationChoose == Choose.FIND) {
            outputView.printStation(stationService.getStationNames());
        }
    }

    private void addStation() {
        String station = inputView.addStation();
        stationService.addStation(station);
        outputView.printSuccessMakeStation();
    }

    private void deleteStation() {
        String station = inputView.deleteStation();
        stationService.deleteStation(station);
        outputView.printSuccessDeleteStation();
    }

    private void startLineFunction() {
        Choose lineChoose = inputView.getLineChoose();
        if (lineChoose == Choose.ADD) {
            addLine();
        }
        if (lineChoose == Choose.DELETE) {
            deleteLine();
        }
        if (lineChoose == Choose.FIND) {
            outputView.printLine(stationService.getLines());
        }
    }

    private void addLine() {
        String line = inputView.addLine();
        String startStation = inputView.addStartStation();
        String endStation = inputView.addEndStation();
        stationService.makeLine(line, startStation, endStation);
        outputView.printSuccessMakeLine();
    }

    private void deleteLine() {
        String line = inputView.deleteLine();
        stationService.deleteLine(line);
        outputView.printSuccessDeleteLine();
    }

    private void startPanelFunction() {
        Choose panelChoose = inputView.getPanelChoose();
        if (panelChoose == Choose.ADD) {
            addPanel();
        }
        if (panelChoose == Choose.DELETE) {
            deletePanel();
        }
    }

    private void addPanel() {
        String line = inputView.getLineForPanel();
        String station = inputView.getStationForPanel();
        int index = inputView.getIndexForPanel();
        stationService.insertStation(line, station, index);
        outputView.printSuccessMakePanel();
    }

    private void deletePanel() {
        String line = inputView.getLineForDeletePanel();
        String station = inputView.getStationForDeletePanel();
        stationService.deleteStation(line, station);
        outputView.printSuccessDeletePanel();
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
