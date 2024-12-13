package subway.view;

import java.util.List;
import subway.dto.StationAllInfo;
import subway.dto.StationInfo;

public class OutputView {
    private static final String PREFIX = "[INFO] ";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void printError(String error) {
        System.out.print(error + LINE_SEPARATOR);
    }

    // 역
    public void printSuccessMakeStation() {
        printInfo("지하철 역이 등록되었습니다.");
    }

    public void printStation(List<String> names) {
        System.out.print("## 역 목록" + LINE_SEPARATOR);
        names.forEach(this::printInfo);
    }

    public void printSuccessDeleteStation() {
        printInfo("지하철 역이 삭제되었습니다.");
    }

    // 노선
    public void printSuccessMakeLine() {
        printInfo("지하철 노선이 등록되었습니다.");
    }

    public void printLine(List<String> names) {
        System.out.print("## 노선 목록" + LINE_SEPARATOR);
        names.forEach(this::printInfo);
    }

    public void printSuccessDeleteLine() {
        printInfo("지하철 노선이 삭제되었습니다.");
    }

    // 구간
    public void printSuccessMakePanel() {
        printInfo("구간이 등록되었습니다.");
    }

    public void printSuccessDeletePanel() {
        printInfo("구간이 삭제되었습니다.");
    }

    public void printAllSubwayInfo(StationAllInfo stationAllInfo) {
        List<StationInfo> infos = stationAllInfo.getInfos();
        infos.forEach(this::printSubwayInfo);
    }

    private void printSubwayInfo(StationInfo stationInfo) {
        printInfo(stationInfo.getLineName());
        printInfo("---");
        for (String station : stationInfo.getStations()) {
            printInfo(station);
        }
        System.out.print(LINE_SEPARATOR);
    }

    private void printInfo(String info) {
        System.out.print(PREFIX + info + LINE_SEPARATOR);
    }

}
