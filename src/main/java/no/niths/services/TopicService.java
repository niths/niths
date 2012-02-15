package no.niths.services;

import java.util.List;

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
        repo.create(topic);
    }

    public Topic getTopicById(Long id) {
    	return repo.getById(id);
   }

    public List<Topic> getAllTopics(Topic topic) {
    	return repo.getAll(topic);
    }

    public void updateTopic(Topic topic) {
        repo.update(topic);
    }

    public boolean deleteTopic(Long id) {
        return repo.delete(id);
    }
}