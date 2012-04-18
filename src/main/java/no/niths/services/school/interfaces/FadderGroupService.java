package no.niths.services.school.interfaces;

import no.niths.domain.school.FadderGroup;
import no.niths.services.interfaces.GenericService;

public interface FadderGroupService extends GenericService<FadderGroup> {

    void addLeader(Long groupId, Long studentId);

    void removeLeader(Long groupId, Long studentId);

    void removeAllLeaders(Long groupId);

    void addChild(Long groupId, Long studentId);

    void removeChild(Long groupId, Long studentId);

    void removeChildren(Long groupId, Long [] studentIds);

    void removeAllChildren(Long groupId);
}