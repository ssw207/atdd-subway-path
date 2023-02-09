package nextstep.subway.unit;

import nextstep.subway.domain.Section;
import nextstep.subway.domain.SectionDeleteAction;
import nextstep.subway.exception.CanNotDeleteSectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static nextstep.subway.fixture.SectionFixture.createSection;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SectionDeleteActionTest {

    private static final long stationId1 = 0L;
    private static final long stationId2 = 1L;
    private static final long stationId3 = 2L;

    private List<Section> sections;

    @BeforeEach
    void setUp() {
        sections = new ArrayList(Arrays.asList(
                createSection(stationId1, stationId2, 10 ),
                createSection(stationId2, stationId3, 2)
        ));
    }

    @Test
    void 구간이_하나면_삭제_실패() {
        sections = new ArrayList(Arrays.asList(createSection(stationId1, stationId2)));

        assertThatThrownBy(() -> SectionDeleteAction.of(sections, stationId1)).isInstanceOf(CanNotDeleteSectionException.class);
    }

    @Test
    void 일치하는_구간이_없으면_삭제_실패() {
        assertThatThrownBy(() -> SectionDeleteAction.of(sections, 3L)).isInstanceOf(CanNotDeleteSectionException.class);
    }

    @Test
    void 처음_구간_삭제_액션_생성() {
        SectionDeleteAction action = SectionDeleteAction.of(sections, stationId1);
        assertThat(action).isEqualTo(SectionDeleteAction.UP_STATION);
    }

    @Test
    void 처음_구간_삭제() {
        SectionDeleteAction action = SectionDeleteAction.of(sections, stationId1);
        action.delete(sections, stationId1);

        assertThat(sections).hasSize(1);
        Section section = sections.get(0);
        assertThat(section.getUpStationId()).isEqualTo(stationId2);
        assertThat(section.getDownStationId()).isEqualTo(stationId3);
    }

    @Test
    void 중간_구간_삭제_액션_생성() {

    }

    @Test
    void 마지막_구간_삭제_액션_생성() {

    }
}