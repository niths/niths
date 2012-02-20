package no.niths.services;

import java.util.List;

import no.niths.domain.Subject;
import no.niths.infrastructure.interfaces.SubjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TopicService {

    @Autowired
    private SubjectRepository repo;

    public void createTopic(Subject topic) {
        repo.create(topic);
    }

    public Subject getById(Long id) {
    	return repo.getById(id);
   }

    public List<Subject> getAllTopics(Subject topic) {
    	return repo.getAll(topic);
    }

    public void updateTopic(Subject topic) {
        repo.update(topic);
    }

    public boolean deleteTopic(Long id) {
        return repo.delete(id);
    }
}