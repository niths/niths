package no.niths.infrastructure.school;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= { TestAppConfig.class, HibernateConfig.class })
public class LockerRepositoryTest {

}
