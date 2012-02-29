package no.niths.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FadderGroupTest {
    private static final Long ID = 1L;
    private static final Integer GROUP_ID = 1;

    private static final Logger logger = LoggerFactory
                    .getLogger(FadderGroupTest.class);

    @Test
    public void testShouldGenerateNewFadderGroup() {
        FadderGroup fadderGroup = new FadderGroup();
        fadderGroup.setId(ID);
        fadderGroup.setGroupId(GROUP_ID);

        assertThat(ID, is(equalTo(fadderGroup.getId())));
        assertThat(GROUP_ID, is(equalTo(fadderGroup.getGroupId())));
    }
}
