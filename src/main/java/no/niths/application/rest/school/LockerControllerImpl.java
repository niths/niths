package no.niths.application.rest.school;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.school.interfaces.LockerController;
import no.niths.common.AppConstants;
import no.niths.domain.school.Locker;
import no.niths.services.interfaces.GenericService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AppConstants.LOCKERS)
public class LockerControllerImpl extends AbstractRESTControllerImpl<Locker>
        implements LockerController {

    private final Logger logger = LoggerFactory.getLogger(Locker.class);

    @Override
    public GenericService<Locker> getService() {
        return null;
    }

    @Override
    public ListAdapter<Locker> getList() {
        // TODO Auto-generated method stub
        return null;
    }

}