package subway.dto;

import java.util.List;

public class StationAllInfo {
    private final List<StationInfo> infos;

    public StationAllInfo(List<StationInfo> infos) {
        this.infos = infos;
    }

    public List<StationInfo> getInfos() {
        return infos;
    }
}
