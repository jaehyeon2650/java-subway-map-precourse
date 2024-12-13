package subway.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import subway.constant.Choose;
import subway.constant.MainChoose;
import subway.exception.ErrorMessage;
import subway.exception.StationException;

public class InputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public MainChoose getMainChoose() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            printMain();
            printLineSeparatorWithInput("## 원하는 기능을 선택하세요.");
            String input = reader.readLine();
            return MainChoose.findMainChoose(input);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public String addStation() {
        printLineSeparatorWithInput("## 등록할 역 이름을 입력하세요.");
        return getAnswer();
    }

    public String addLine() {
        printLineSeparatorWithInput("## 등록할 노선 이름을 입력하세요.");
        return getAnswer();
    }

    public String addStartStation() {
        printLineSeparatorWithInput("## 등록할 노선의 상행 종점역 이름을 입력하세요.");
        return getAnswer();
    }

    public String addEndStation() {
        printLineSeparatorWithInput("## 등록할 노선의 하행 종점역 이름을 입력하세요.");
        return getAnswer();
    }

    public String deleteStation() {
        printLineSeparatorWithInput("## 삭제할 역 이름을 입력하세요.");
        return getAnswer();
    }

    public String deleteLine() {
        printLineSeparatorWithInput("## 삭제할 노선 이름을 입력하세요.");
        return getAnswer();
    }

    public Choose getStationChoose() {
        printStationMenu();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            printLineSeparatorWithInput("## 원하는 기능을 선택하세요.");
            String input = reader.readLine();
            return Choose.findChoose(input);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public Choose getLineChoose() {
        printLineMenu();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            printLineSeparatorWithInput("## 원하는 기능을 선택하세요.");
            String input = reader.readLine();
            return Choose.findChoose(input);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public Choose getPanelChoose() {
        printPanelMenu();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            printLineSeparatorWithInput("## 원하는 기능을 선택하세요.");
            String input = reader.readLine();
            return Choose.findChoose(input);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public String getLineForPanel() {
        printLineSeparatorWithInput("## 노선을 입력하세요.");
        return getAnswer();
    }

    public String getStationForPanel() {
        printLineSeparatorWithInput("## 역이름을 입력하세요.");
        return getAnswer();
    }

    public String getLineForDeletePanel() {
        printLineSeparatorWithInput("## 삭제할 구간의 노선을 입력하세요.");
        return getAnswer();
    }

    public String getStationForDeletePanel() {
        printLineSeparatorWithInput("## 삭제할 구간의 역을 입력하세요.");
        return getAnswer();
    }

    public int getIndexForPanel() {
        printLineSeparatorWithInput("## 순서를 입력하세요.");
        try {
            return Integer.parseInt(getAnswer());
        } catch (NumberFormatException e) {
            throw StationException.from(ErrorMessage.INVALID_INDEX_STATION_IN_LINE);
        }
    }

    private String getAnswer() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }


    private void printStationMenu() {
        printLineSeparatorWithInput("## 역 관리 화면");
        printLineSeparatorWithInput("1. 역 등록");
        printLineSeparatorWithInput("2. 역 삭제");
        printLineSeparatorWithInput("3. 역 조회");
        printLineSeparatorWithInput("B. 돌아가기");
        printLineSeparator();
    }

    private void printLineMenu() {
        printLineSeparatorWithInput("## 노션 관리 화면");
        printLineSeparatorWithInput("1. 노션 등록");
        printLineSeparatorWithInput("2. 노션 삭제");
        printLineSeparatorWithInput("3. 노션 조회");
        printLineSeparatorWithInput("B. 돌아가기");
        printLineSeparator();
    }

    private void printPanelMenu() {
        printLineSeparatorWithInput("## 구간 관리 화면");
        printLineSeparatorWithInput("1. 구간 등록");
        printLineSeparatorWithInput("2. 구간 삭제");
        printLineSeparatorWithInput("B. 돌아가기");
        printLineSeparator();
    }

    private void printMain() {
        printLineSeparatorWithInput("## 메인 화면");
        printLineSeparatorWithInput("1. 역 관리");
        printLineSeparatorWithInput("2. 노선 관리");
        printLineSeparatorWithInput("3. 구간 관리");
        printLineSeparatorWithInput("4. 지하철 노선도 출력");
        printLineSeparatorWithInput("Q. 종료");
        printLineSeparator();
    }

    private void printLineSeparatorWithInput(String input) {
        System.out.print(input + LINE_SEPARATOR);
    }

    private void printLineSeparator() {
        System.out.print(LINE_SEPARATOR);
    }
}
