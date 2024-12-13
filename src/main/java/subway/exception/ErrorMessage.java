package subway.exception;

public enum ErrorMessage {
    NO_STATION_IN_LINE("해당 지하철역은 노선에 존재하지 않습니다."),
    INVALID_LINE_NAME_LENGTH("노선의 이름은 2글자 이상이어야합니다."),
    INVALID_DUPLICATE_STATION_IN_LINE("노선에 중복된 지하철역을 넣을 수 없습니다."),
    INVALID_INDEX_STATION_IN_LINE("해당 순서에는 지하철 역을 넣을 수 없습니다."),
    CANT_REMOVE_STATION("노션에 포함된 역이 2개 이하이기에 삭제가 불가능합니다."),
    NO_LINE("해당 노선은 존재하지 않습니다."),
    ALREADY_EXIST_LINE("해당 노선은 이미 존재합니다."),
    INVALID_STATION_NAME_LENGTH("지하철 역의 이름은 2글자 이상이어야합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
