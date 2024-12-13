package subway.constant;

import java.util.Arrays;
import subway.exception.ErrorMessage;
import subway.exception.StationException;

public enum MainChoose {
    STATION("1"), LINE("2"), PANEL("3"), INFO("4"), EXIT("Q");
    private final String choose;

    MainChoose(String choose) {
        this.choose = choose;
    }

    public static MainChoose findMainChoose(String input) {
        return Arrays.stream(MainChoose.values()).filter(mainChoose -> {
            return mainChoose.choose.equals(input);
        }).findFirst().orElseThrow(() -> StationException.from(ErrorMessage.INVALID_INPUT));
    }
}
