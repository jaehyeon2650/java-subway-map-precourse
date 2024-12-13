package subway.constant;

import java.util.Arrays;
import subway.exception.ErrorMessage;
import subway.exception.StationException;

public enum Choose {
    ADD("1"), DELETE("2"), FIND("3"), BACK("B");
    private final String choose;

    Choose(String choose) {
        this.choose = choose;
    }

    public static Choose findChoose(String input) {
        return Arrays.stream(Choose.values()).filter(mainChoose -> {
            return mainChoose.choose.equals(input);
        }).findFirst().orElseThrow(() -> StationException.from(ErrorMessage.INVALID_INPUT));
    }
}
