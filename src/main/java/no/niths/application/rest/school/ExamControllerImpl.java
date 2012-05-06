package no.niths.application.rest.school;

import java.util.List;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.RESTConstants;
import no.niths.application.rest.helper.TimeDTO;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.school.ExamList;
import no.niths.application.rest.school.interfaces.ExamController;
import no.niths.common.constants.DomainConstantNames;
import no.niths.common.helpers.ValidationHelper;
import no.niths.domain.school.Exam;
import no.niths.domain.school.constants.ExamType;
import no.niths.services.interfaces.GenericService;
import no.niths.services.school.interfaces.ExamService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller for exam
 * has the basic CRUD methods and
 * methods too add and remove room
 * and subject
 * in addition too methods for getExamsBetweenDates
 *
 * For the URL too get Exam add /exams
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
@Controller
@RequestMapping(DomainConstantNames.EXAMS)
public class ExamControllerImpl extends AbstractRESTControllerImpl<Exam>
        implements ExamController {

    private Logger logger = LoggerFactory.getLogger(ExamControllerImpl.class);
    
    @Autowired
    private ExamService examService;

    private ExamList examList = new ExamList();

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "{examId}/room/{roomId}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK, reason = "Room Added")
    public void addRoom(@PathVariable Long examId, @PathVariable Long roomId) {
        examService.addRoom(examId, roomId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "{examId}/room/{roomId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK, reason = "Room Removed")
    public void removeRoom(@PathVariable Long examId, @PathVariable Long roomId) {
        examService.removeRoom(examId, roomId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "{examId}/subject/{subjectId}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK, reason = "Subject Added")
    public void addSubject(@PathVariable Long examId,
            @PathVariable Long subjectId) {
        examService.addSubject(examId, subjectId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "{examId}/subject", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK, reason = "Subject Removed")
    public void removeSubject(@PathVariable Long examId) {
        examService.removeSubject(examId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenericService<Exam> getService() {
        return examService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListAdapter<Exam> getList() {
        return examList;
    }

    @Override
    @RequestMapping(value = "dates", method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
    @ResponseBody
    public List<Exam> getExamsBetweenDates(TimeDTO timeDTO) {
        
        logger.debug(timeDTO.toString());
        ValidationHelper.isObjectNull(timeDTO.getStartTime());
        if(timeDTO.getEndTime() != null){
            renewList(examService.getExamsBetweenDates(timeDTO.getStartTimeCal(), timeDTO.getEndTimeCal()));
        }else{
            renewList(examService.getExamsBetweenDates(timeDTO.getStartTimeCal(), null));
        }
        return examList;
    }

    @Override
    public List<ExamType> getExamTypes() {
        return null;
    }
}