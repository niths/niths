package no.niths.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FadderUkaTest {
    private static final Long ID = 1L;
    private static final Integer GROUP_ID = 1;

    private static final Logger logger = LoggerFactory
                    .getLogger(FadderUkaTest.class);

    @Test
    public void testShouldGenerateNewFadderUke() {
        FadderUka fadderUka = new FadderUka();
        fadderUka.setId(ID);
        fadderUka.setGroupId(GROUP_ID);

        assertThat(ID, is(equalTo(fadderUka.getId())));
        assertThat(GROUP_ID, is(equalTo(fadderUka.getGroupId())));
    }
}
