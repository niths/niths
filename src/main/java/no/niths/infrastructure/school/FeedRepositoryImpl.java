package no.niths.infrastructure.school;

import java.util.GregorianCalendar;

import no.niths.domain.school.Feed;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.school.interfaces.FeedRepoistory;

import org.springframework.stereotype.Repository;

@Repository
public class FeedRepositoryImpl extends AbstractGenericRepositoryImpl<Feed>
		implements FeedRepoistory {

	public FeedRepositoryImpl() {
		super(Feed.class, new Feed());
	}
	
	@Override
	public Long create(Feed domain) {
		domain.setPublished(new GregorianCalendar());
		return super.create(domain);
	}

	@Override
	public void update(Feed domain) {
		domain.setPublished(new GregorianCalendar());
		super.update(domain);
	}

}
