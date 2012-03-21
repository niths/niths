package no.niths.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import no.niths.domain.Feed;
import no.niths.infrastructure.interfaces.FeedRepoistory;
import no.niths.services.interfaces.FeedService;

@Service
@Transactional
public class FeedServiceImpl implements FeedService {

	@Autowired
	private FeedRepoistory repo;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long create(Feed domain) {
		return repo.create(domain);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Feed> getAll(Feed domain) {
		return repo.getAll(domain);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Feed getById(long id) {
		Feed feed = repo.getById(id);
		
		if(feed != null){
			if(feed.getStudent()!=null)
				feed.getStudent().getEmail();
			if(feed.getLocation()!=null)
				feed.getLocation().getPlace();
		}
		
		return feed;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Feed domain) {
		repo.update(domain);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete(long id) {
		return repo.delete(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void hibernateDelete(long id) {
		repo.hibernateDelete(id);
	}

}
