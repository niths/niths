package no.niths.infrastructure;

import java.util.List;

import no.niths.domain.Course;
import no.niths.domain.Topic;
import no.niths.infrastructure.interfaces.CoursesRepository;
import no.niths.infrastructure.interfaces.TopicsRepository;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TopicsRepositoryImpl implements TopicsRepository {

    @Autowired
    private SessionFactory session;

    @Override
    @Transactional(readOnly = false)
    public Long createTopic(Topic topic) {
        return (Long) session.getCurrentSession().save(topic);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Topic> getAllTopics() {
        return session.getCurrentSession()
                .createQuery("FROM " + Topic.class.getName()).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Topic> getAllTopics(Topic topic){
    	return session.getCurrentSession()
    			.createCriteria(Topic.class).add(Example.create(topic)).list();
    }

    @Override
    public Topic getTopicById(Long id) {
        return (Topic) session.getCurrentSession().get(Topic.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Topic> getTopicByName(String name) {
        return session
                .getCurrentSession()
                .createQuery(
                        "FROM " + Topic.class.getName()
                        + " c WHERE name=:name")
                        .setString("name", name).list();
    }
    

    @Override
    @Transactional(readOnly = false)
    public void updateTopic(Topic topic) {
        session.getCurrentSession().update(topic);
    }

    @Override
    public boolean deleteTopic(Long id) {
        Query query = session.getCurrentSession().createQuery(
        		"delete " + Topic.class.getSimpleName() + " where id = :id");
        query.setParameter("id", id);
        return (1 == query.executeUpdate());
    }
}