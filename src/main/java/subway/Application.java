package subway;

import java.util.Scanner;
import subway.config.Config;
import subway.controller.StationController;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        // TODO: 프로그램 구현
        Config config = new Config();
        StationController stationController = config.stationController();
        stationController.start();
    }
}
