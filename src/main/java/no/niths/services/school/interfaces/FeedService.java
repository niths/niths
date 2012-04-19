package no.niths.services.school.interfaces;

import no.niths.domain.school.Feed;
import no.niths.services.interfaces.GenericService;

public interface FeedService extends GenericService<Feed>{

	void addLocation(Long feedId, Long locationId);

	void removeLocation(Long feedId);

	void addStudent(Long feedId, Long studentId);

	void removeStudent(Long feedId);

}
