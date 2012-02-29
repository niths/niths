package no.niths.application.rest;

import no.niths.application.rest.interfaces.FadderGroupController;
import no.niths.application.rest.interfaces.SubjectController;
import no.niths.application.rest.lists.FadderGroupList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.SubjectList;
import no.niths.common.AppConstants;
import no.niths.domain.FadderGroup;
import no.niths.domain.Subject;
import no.niths.services.interfaces.FadderGroupService;
import no.niths.services.interfaces.GenericService;
import no.niths.services.interfaces.SubjectService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Controller for subjects
 *
 */
@Controller
@RequestMapping(AppConstants.FADDER_GROUP)
public class FadderGroupControllerImpl extends AbstractRESTControllerImpl<FadderGroup> implements FadderGroupController{

	private static final Logger logger = LoggerFactory
			.getLogger(FadderGroupControllerImpl.class);

	@Autowired
	private FadderGroupService service;

	private FadderGroupList fadderGroupList = new FadderGroupList();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GenericService<FadderGroup> getService() {
		return service;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListAdapter<FadderGroup> getList() {
		return fadderGroupList;
	}


}
