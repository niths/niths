package no.niths.application.rest;

import java.util.ArrayList;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.lists.SubjectList;
import no.niths.common.AppConstants;
import no.niths.domain.Subject;
import no.niths.services.TopicService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(AppConstants.TOPICS)
public class SubjectController implements RESTController<Subject> {

	private static final Logger logger = LoggerFactory
			.getLogger(SubjectController.class);

	@Autowired
	private TopicService service;

	private SubjectList topicList = new SubjectList();

	/**
	 * 
	 * @param Course
	 *            The course to be created
	 */
	@Override
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED, reason = "Topic created")
	public void create(@RequestBody Subject topic) {
		service.createTopic(topic);
	}

	/**
	 * 
	 * @param long The course's id
	 * @return The course identified by the id
	 */
	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public Subject getById(@PathVariable Long id) {
		return service.getTopicById(id);
	}

	/**
	 * 
	 * @return All courses
	 */
	@Override
	@RequestMapping(method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public ArrayList<Subject> getAll(Subject topic) {
		logger.info(topic.toString());

		topicList.clear();
		topicList.addAll(service.getAllTopics(topic));
		topicList.setData(topicList);
		if (topicList.size() == 0) {
			throw new ObjectNotFoundException();
		}
		return topicList;
	}

	/**
	 * 
	 * @param Course
	 *            The Course to update
	 */
	@Override
	@RequestMapping(value = { "", "{id}" }, method = RequestMethod.PUT, headers = RESTConstants.CONTENT_TYPE_HEADER)
	@ResponseStatus(value = HttpStatus.OK, reason = "Topic updated")
	public void update(@RequestBody Subject topic, @PathVariable Long id) {

		// If the ID is only provided through the URL.
		if (id != null)
			topic.setId(id);

		service.updateTopic(topic);
	}

	/**
	 * 
	 * @param long The id of the Course to delete
	 */
	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK, reason = "Topic deleted")
	public void delete(@PathVariable Long id) {
		if (!service.deleteTopic(id)) {
			throw new ObjectNotFoundException();
		}
	}
}