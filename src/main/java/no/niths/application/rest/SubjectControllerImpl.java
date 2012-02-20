package no.niths.application.rest;

import no.niths.application.rest.interfaces.SubjectController;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.SubjectList;
import no.niths.common.AppConstants;
import no.niths.domain.Subject;
import no.niths.services.interfaces.GenericService;
import no.niths.services.interfaces.SubjectService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AppConstants.SUBJECTS)
public class SubjectControllerImpl extends AbstractRESTControllerImpl<Subject> implements SubjectController{

	private static final Logger logger = LoggerFactory
			.getLogger(SubjectControllerImpl.class);

	@Autowired
	private SubjectService service;

	private SubjectList subjectList = new SubjectList();

	@Override
	public GenericService<Subject> getService() {
		return service;
	}

	@Override
	public ListAdapter<Subject> getList() {
		return subjectList;
	}


}
