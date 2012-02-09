package no.niths.services;

import java.util.List;

import no.niths.domain.Course;
import no.niths.domain.Topic;
import no.niths.infrastructure.interfaces.TopicsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TopicService {

    @Autowired
    private TopicsRepository repo;

    public void createTopic(Topic topic) {
        repo.createTopic(topic);
    }

    public Topic getTopicById(Long id) {
    	return repo.getTopicById(id);
   }

    public List<Topic> getCourseByName(String name) {
    	return repo.getTopicByName(name);
    }

    public List<Topic> getAllTopics() {
        return repo.getAllTopics();
    }
    public List<Topic> getAllTopics(Topic topic) {
    	return repo.getAllTopics(topic);
    }

    public void updateTopic(Topic topic) {
        repo.updateTopic(topic);
    }

    public void deleteTopic(Long id) {
        repo.deleteTopic(id);
    }
}