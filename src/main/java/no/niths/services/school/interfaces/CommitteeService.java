package no.niths.services.school.interfaces;

import no.niths.domain.school.Committee;
import no.niths.services.interfaces.GenericService;

public interface CommitteeService extends GenericService<Committee> {

	void addLeader(Long committeeId, Long studentId);

	void removeLeader(Long committeeId, Long studentId);

	void addEvent(Long committeeId, Long eventId);

	void removeEvent(Long committeeId, Long eventId);
	
}