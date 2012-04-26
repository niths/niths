package no.niths.infrastructure.school;

import java.util.GregorianCalendar;

import no.niths.domain.school.Feed;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.school.interfaces.FeedRepoistory;

import org.springframework.stereotype.Repository;
/**
 * Repository class for Feed
 *
 */
@Repository
public class FeedRepositoryImpl extends AbstractGenericRepositoryImpl<Feed>
		implements FeedRepoistory {

	public FeedRepositoryImpl() {
		super(Feed.class, new Feed());
	}
	
	/**
	 * Persists a Feed
	 * 
	 * Sets the published date to todays date
	 * 
	 * @param domain the feed to persist
	 */
	@Override
	public Long create(Feed domain) {
		domain.setPublished(new GregorianCalendar());
		return super.create(domain);
	}

	/**
	 * Updates a Feed
	 * 
	 * Sets the published date to todays date
	 * 
	 * @param domain the feed to persist
	 */
	@Override
	public void update(Feed domain) {
		domain.setPublished(new GregorianCalendar());
		super.update(domain);
	}

}
