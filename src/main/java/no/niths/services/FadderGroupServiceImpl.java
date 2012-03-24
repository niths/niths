package no.niths.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import no.niths.aop.ApiEvent;
import no.niths.domain.FadderGroup;
import no.niths.infrastructure.interfaces.FadderGroupRepository;
import no.niths.services.interfaces.FadderGroupService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FadderGroupServiceImpl implements FadderGroupService {

	private Logger logger = org.slf4j.LoggerFactory
			.getLogger(FadderGroupServiceImpl.class);
	private CustomBeanUtilsBean beanCopy = new CustomBeanUtilsBean();

	
	@Autowired
	private FadderGroupRepository repo;

	@ApiEvent(title = "Faddergroup created")
	public Long create(FadderGroup group) {
		return repo.create(group);
	}

	public FadderGroup getById(long id) {
		FadderGroup group = repo.getById(id);
		if (group != null) {
			group.getFadderChildren().size();
			group.getLeaders().size();
		}
		return group;
	}

	public List<FadderGroup> getAll(FadderGroup group) {
		List<FadderGroup> all = repo.getAll(group);
		return all;
	}

	@ApiEvent(title = "Faddergroup updated")
	public void update(FadderGroup group) {
		FadderGroup FadderToUpdate = repo.getById(group.getId());
		try {
			beanCopy.copyProperties(FadderToUpdate, group);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error",e);
			e.printStackTrace();
		}
		repo.update(FadderToUpdate);
	}

	public boolean delete(long id) {
		return repo.delete(id);
	}

	@Override
	public void hibernateDelete(long id) {
		repo.hibernateDelete(id);
	}

	@Override
	public void updateForFadderLeaderAndChildren(FadderGroup group) {
		repo.update(group);
	}
}