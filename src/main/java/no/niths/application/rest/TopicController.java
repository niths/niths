package no.niths.application.rest;

import java.util.ArrayList;

import no.niths.application.rest.lists.CourseList;
import no.niths.application.rest.lists.TopicList;
import no.niths.common.AppConstants;
import no.niths.domain.Course;
import no.niths.domain.Student;
import no.niths.domain.Topic;
import no.niths.services.CourseService;
import no.niths.services.TopicService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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
public class TopicController implements RESTController<Topic> {
	
	private static final Logger logger = LoggerFactory
			.getLogger(TopicController.class);

    @Autowired
    private TopicService service;

    private TopicList topicList = new TopicList();


    /**
     * 
     * @param Course The course to be created
     */
    @Override
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Topic created")
    public void create(@RequestBody Topic topic) {
        service.createTopic(topic);
    }

    /**
     * 
     * @param long The course's id
     * @return The course identified by the id
     */
    @Override
    @RequestMapping(
            value   = "{id}",
            method  = RequestMethod.GET,
            headers = RESTConstants.ACCEPT_HEADER)
    @ResponseBody
    public Topic getById(@PathVariable Long id) {
        return service.getTopicById(id);
    }

    /**
     * 
     * @return All courses
     */
    @Override
    @RequestMapping(
            method  = RequestMethod.GET,
            headers = RESTConstants.ACCEPT_HEADER)
    @ResponseBody
    public ArrayList<Topic> getAll(Topic topic, HttpEntity<byte[]> request) {
    	logger.info(topic.toString());
    	
      	 final String FIRST =
                   request.getHeaders().getFirst(RESTConstants.ACCEPT);
      	 
          if (topic.isEmpty()) {
              if (FIRST.equals(RESTConstants.JSON)) {
                  return (ArrayList<Topic>) service.getAllTopics();
              } else if (FIRST.equals(RESTConstants.XML)) {
                  topicList.setData(service.getAllTopics());
                  return topicList;
              }
          } else {
          	 if (FIRST.equals(RESTConstants.JSON)) {
                   return (ArrayList<Topic>) service.getAllTopics(topic);
               } else if (FIRST.equals(RESTConstants.XML)) {
                   topicList.setData(service.getAllTopics(topic));
                   return topicList;
               }
          }
          return null;
    }

    /**
     * 
     * @param Course The Course to update
     */
    @Override
    @RequestMapping(
            value   = {"", "{id}"},
            method  = RequestMethod.PUT,
            headers = RESTConstants.CONTENT_TYPE_HEADER)
    @ResponseStatus(value = HttpStatus.OK, reason = "Topic updated")
    public void update(
            @RequestBody Topic topic,
            @PathVariable Long id) {

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
    @RequestMapping(
            value  = "{id}",
            method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK, reason = "Topic deleted")
    public void delete(@PathVariable Long id) {
        service.deleteTopic(id);
    }
}