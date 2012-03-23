package no.niths.application.rest;

import java.util.ArrayList;

import no.niths.application.rest.interfaces.FeedController;
import no.niths.application.rest.lists.FeedList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.AppConstants;
import no.niths.domain.Feed;
import no.niths.services.interfaces.FeedService;
import no.niths.services.interfaces.GenericService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AppConstants.FEEDS)
public class FeedControllerImpl extends AbstractRESTControllerImpl<Feed>
		implements FeedController {

	private Logger logger = org.slf4j.LoggerFactory
			.getLogger(FeedController.class);

	@Autowired
	private FeedService service;

	private FeedList list = new FeedList();

	@Override
	public ArrayList<Feed> getAll(Feed domain) {
		
		super.getAll(domain);
		
		for (Feed l : list) {
			l.setStudent(null);
			l.setLocation(null);
		}
		return list;
	}
	
	@Override
	public Feed getById(@PathVariable Long id) {
		logger.debug("get by id in controller so good so far "  + id);
		Feed feed = service.getById(id);
		if(feed.getStudent() != null){
			feed.getStudent().setCommittees(null);
			feed.getStudent().setCommitteesLeader(null);
			feed.getStudent().setCourses(null);
			feed.getStudent().setFadderGroup(null);
			feed.getStudent().setGroupLeaders(null);
			feed.getStudent().setFeeds(null);
			feed.getStudent().setRoles(null);
		}
		return feed;
	}

	@Override
	public GenericService<Feed> getService() {
		return service;
	}

	@Override
	public ListAdapter<Feed> getList() {
		return list;
	}

}
