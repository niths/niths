package no.niths.domain.school;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import no.niths.domain.school.FadderGroup;

import org.junit.Test;

public class FadderGroupTest {
    private static final Long ID = 1L;
    private static final Integer GROUP_ID = 1;

    @Test
    public void testShouldGenerateNewFadderGroup() {
        FadderGroup fadderGroup = new FadderGroup();
        fadderGroup.setId(ID);
        fadderGroup.setGroupNumber(GROUP_ID);

        assertThat(ID, is(equalTo(fadderGroup.getId())));
        assertThat(GROUP_ID, is(equalTo(fadderGroup.getGroupNumber())));
    }
}
