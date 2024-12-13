package subway.exception;

public class StationException extends IllegalArgumentException {
    private static final String PREFIX = "[ERROR] ";

    private StationException(ErrorMessage errorMessage) {
        super(PREFIX + errorMessage.getMessage());
    }

    public static StationException from(ErrorMessage errorMessage) {
        return new StationException(errorMessage);
    }
}
