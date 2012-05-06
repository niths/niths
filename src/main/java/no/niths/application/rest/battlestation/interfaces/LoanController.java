package no.niths.application.rest.battlestation.interfaces;

import java.util.List;

import no.niths.application.rest.helper.TimeDTO;
import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.battlestation.Loan;

/**
 * Controller for loan
 * has the basic CRUD methods and
 * methods too add and remove console
 * and student
 * in addition too method for getLoansBetweenDates,
 *
 * For the URL too get Loan add /loans
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
public interface LoanController extends GenericRESTController<Loan> {

    /**
     * Creates a loan with a console and a student + a return date
     *
     * Too add console add /console/{consoleId}/student/{studentId}/return/{returnDate}  
     * 
     * too the URL
     * 
     *
     * Use the POST method
	 * 
	 * @param consoleId
	 * @param studentId
	 * @param retrunDate format = dd/MM/YYYY-hh:mm
	 */
    void creatLoan(Long consoleId, Long studentId,String retrunDate);

  
    
    /**
     * Returns loans between or from timeDTO's startTime or / and endTime
     *
     * Too get loans between dates add /dates?startTime={StartTime}&endTime={endTime}
     * too the URL on the format "dd/MM/YYYY-hh:mm
     *
     * Use the GET method
     *
     * @param timeDTO date for finding loans between two dates or after one
     * @return list of loans
     */
    List<Loan> getLoansBetweenDates(TimeDTO timeDTO);
}
