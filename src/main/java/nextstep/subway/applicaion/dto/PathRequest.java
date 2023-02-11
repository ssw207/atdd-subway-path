package nextstep.subway.applicaion.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;

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
}