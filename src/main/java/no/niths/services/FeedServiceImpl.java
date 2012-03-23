package no.niths.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import no.niths.domain.Feed;
import no.niths.infrastructure.interfaces.FeedRepoistory;
import no.niths.services.interfaces.FeedService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FeedServiceImpl implements FeedService {
	
	private Logger logger = LoggerFactory.getLogger(FeedServiceImpl.class);
	private CustomBeanUtilsBean beanCopy = new CustomBeanUtilsBean();

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
		
		logger.debug("get by id in service so good so far");
		Feed feed = repo.getById(id);
		logger.debug("is shit null? "+(feed==null));
		
		if (feed != null) {
			if (feed.getStudent() != null) {
				feed.getStudent().getEmail();
			}
		}

		return feed;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Feed feed) {
		Feed feedToUpdate = repo.getById(feed.getId());
		try {
			beanCopy.copyProperties(feedToUpdate, feed);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error",e);
			e.printStackTrace();
		}
		repo.update(feedToUpdate);
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
