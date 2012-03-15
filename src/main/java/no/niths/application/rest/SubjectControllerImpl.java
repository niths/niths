package no.niths.application.rest;

import no.niths.application.rest.interfaces.SubjectController;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.SubjectList;
import no.niths.common.AppConstants;
import no.niths.common.SecurityConstants;
import no.niths.domain.Subject;
import no.niths.services.interfaces.GenericService;
import no.niths.services.interfaces.SubjectService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Controller for subjects
 *
 */
@Controller
@RequestMapping(AppConstants.SUBJECTS)
public class SubjectControllerImpl extends AbstractRESTControllerImpl<Subject> implements SubjectController{

	private static final Logger logger = LoggerFactory
			.getLogger(SubjectControllerImpl.class);

	@Autowired
	private SubjectService service;

	private SubjectList subjectList = new SubjectList();

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void create(@RequestBody Subject domain) {
		super.create(domain);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void hibernateDelete(@PathVariable long id) {
		super.hibernateDelete(id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void update(@RequestBody Subject domain) {
		super.update(domain);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public GenericService<Subject> getService() {
		return service;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListAdapter<Subject> getList() {
		return subjectList;
	}


}
