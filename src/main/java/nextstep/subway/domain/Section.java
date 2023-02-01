package nextstep.subway.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "line_id")
    private Line line;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "up_station_id")
    private Station upStation;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "down_station_id")
    private Station downStation;

    private int distance;

    public Section(Line line, Station upStation, Station downStation, int distance) {
        this.line = line;
        this.upStation = upStation;
        this.downStation = downStation;
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public Line getLine() {
        return line;
    }

    public Station getUpStation() {
        return upStation;
    }

    public Station getDownStation() {
        return downStation;
    }

    public int getDistance() {
        return distance;
    }

    public Long getDownStationId() {
        return Optional.ofNullable(downStation)
                .orElseThrow(() -> new IllegalStateException("하행역이 없습니다."))
                .getId();
    }

    public Long getUpStationId() {
        return Optional.ofNullable(upStation)
                .orElseThrow(() -> new IllegalStateException("상행역이 없습니다."))
                .getId();
    }

    public void changeLine(Line newLine) {
        if (line != null && line.equals(newLine)) {
            return;
        }

        if (line != null) {
            line.remove(this);
        }

        newLine.addSection(this);
        line = newLine;
    }

    public void removeLine() {
        if (line == null) {
            return;
        }

        line.remove(this);
        line = null;
    }
}