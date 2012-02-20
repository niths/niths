package no.niths.application.rest;

import java.util.ArrayList;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.lists.SubjectList;
import no.niths.common.AppConstants;
import no.niths.common.ValidationHelper;
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

	private SubjectList subjectList = new SubjectList();

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
		Subject subject = service.getById(id);
		ValidationHelper.isObjectNull(subject);
		return subject;
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

		subjectList.clear();
		subjectList.addAll(service.getAllTopics(topic));
		subjectList.setData(subjectList);
		ValidationHelper.isListEmpty(subjectList);
		return subjectList;
	}


	@Override
	@RequestMapping(value = { "", "{id}" }, method = RequestMethod.PUT, headers = RESTConstants.CONTENT_TYPE_HEADER)
	@ResponseStatus(value = HttpStatus.OK, reason = "Topic updated")
	public void update(@RequestBody Subject topic, @PathVariable Long id) {

		// If the ID is only provided through the URL.
		if (id != null){
			topic.setId(id);
		}
		

		service.updateTopic(topic);
	}

	/**
	 * 
	 * @param id The id of the Subject to delete
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