package nextstep.subway.applicaion.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PathRequest {

    @NotNull
    private Long source;
    @NotNull
    private Long target;

    private PathRequest(Long source, Long target) {
        this.source = source;
        this.target = target;
    }

    public static PathRequest of(Long source, Long target) {
        return new PathRequest(source, target);
    }

    public List<Long> toStationIds() {
        return List.of(source, target);
    }
}
