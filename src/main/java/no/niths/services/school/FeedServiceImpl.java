package no.niths.services.school;

import no.niths.domain.school.Feed;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.school.interfaces.FeedRepoistory;
import no.niths.services.AbstractGenericService;
import no.niths.services.school.interfaces.FeedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedServiceImpl extends AbstractGenericService<Feed> implements FeedService {
	

	@Autowired
	private FeedRepoistory repo;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Feed getById(long id) {
		Feed feed = repo.getById(id);
		if (feed != null) {
			if (feed.getStudent() != null) {
				feed.getStudent().getEmail();
			}
			
			if(feed.getLocation() != null){
				feed.getLocation().getPlace();
			}
			
		}
		return feed;
	}

	@Override
	public GenericRepository<Feed> getRepository() {
		return repo;
	}
}
