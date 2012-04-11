package no.niths.application.rest.interfaces;

import no.niths.domain.Feed;

public interface FeedController extends GenericRESTController<Feed> {

	void addLocation(Long feedId, Long locId);

	void removeLocation(Long feedId);

	void addStudent(Long feedId, Long studentId);

	void removeStudent(Long feedId);
}
