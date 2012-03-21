package no.niths.infrastructure;

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
	public void hibernateDelete(long id) {
		Feed feed = new Feed();
		feed.setId(id);
		getSession().getCurrentSession().delete(feed);

	}

}
