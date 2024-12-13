package subway;

import java.util.Scanner;
import subway.controller.StationController;
import subway.domain.Line;
import subway.domain.Station;
import subway.repository.LineRepository;
import subway.repository.StationRepository;
import subway.service.StationService;
import subway.view.InputView;
import subway.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        // TODO: 프로그램 구현
        OutputView outputView = new OutputView();
        InputView inputView = new InputView();
        LineRepository lineRepository = new LineRepository();
        StationRepository stationRepository = new StationRepository();
        Station station1 = new Station("교대역");
        Station station2 = new Station("강남역");
        Station station3 = new Station("역삼역");
        Station station4 = new Station("남부터미널역");
        Station station5 = new Station("양재역");
        Station station6 = new Station("양재시민의숲역");
        Station station7 = new Station("매봉역");

        stationRepository.addStation(station1);
        stationRepository.addStation(station2);
        stationRepository.addStation(station3);
        stationRepository.addStation(station4);
        stationRepository.addStation(station5);
        stationRepository.addStation(station6);
        stationRepository.addStation(station7);

        Line line1 = new Line("2호선");
        line1.addStation(station1);
        line1.addStation(station2);
        line1.addStation(station3);

        Line line2 = new Line("3호선");
        line2.addStation(station1);
        line2.addStation(station4);
        line2.addStation(station5);
        line2.addStation(station7);
        Line line3 = new Line("신분당선");
        line3.addStation(station2);
        line3.addStation(station5);
        line3.addStation(station6);

        lineRepository.addLine(line1);
        lineRepository.addLine(line2);
        lineRepository.addLine(line3);

        StationService stationService = new StationService(lineRepository, stationRepository);
        StationController stationController = new StationController(outputView, inputView, stationService);
        stationController.start();
    }
}
