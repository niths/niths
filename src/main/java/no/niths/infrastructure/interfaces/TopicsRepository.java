package no.niths.infrastructure.interfaces;

import java.util.List;

import no.niths.domain.Topic;


public interface TopicsRepository   {

    Long createTopic(Topic topic);

    Topic getTopicById(Long id);

    List<Topic> getTopicByName(String name);

    List<Topic> getAllTopics();
    List<Topic> getAllTopics(Topic topic);

    void updateTopic(Topic topic);

    boolean deleteTopic(Long id);
}