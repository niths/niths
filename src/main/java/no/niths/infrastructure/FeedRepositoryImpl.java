package no.niths.infrastructure;

import java.util.GregorianCalendar;

import no.niths.domain.Feed;
import no.niths.infrastructure.interfaces.FeedRepoistory;

import org.springframework.stereotype.Repository;

@Repository
public class FeedRepositoryImpl extends AbstractGenericRepositoryImpl<Feed>
		implements FeedRepoistory {

	public FeedRepositoryImpl() {
		super(Feed.class);
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
	
	@Override
	public void hibernateDelete(long id) {
		Feed feed = new Feed();
		feed.setId(id);
		getSession().getCurrentSession().delete(feed);
	}

}
