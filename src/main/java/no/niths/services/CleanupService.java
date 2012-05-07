package no.niths.services;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import no.niths.domain.APIEvent;
import no.niths.domain.battlestation.Console;
import no.niths.domain.battlestation.Loan;
import no.niths.infrastructure.battlestation.interfaces.LoanRepository;
import no.niths.infrastructure.interfaces.APIEventRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * A service that removes APIevent entries from the database in a Spring
 * "cron job"
 * 
 */
@Service
@Transactional
public class CleanupService {
	private static final Logger logger = LoggerFactory
			.getLogger(CleanupService.class);

	@Autowired
	private APIEventRepository apiRepo;

	@Autowired
	private LoanRepository loanRepo;

	@Autowired
	private MailSenderServiceImpl mailSender;

	private final int max = 100;

	@Scheduled(cron = "0 0 6 * * *")
	/**                | | | | | | 
	 *                 | | | | | | 
	 *                 | | | | | +----- any day of the week. 
	 *                 | | | | +------- any month (September).
	 *                 | | | +--------- any day of the month.
	 *                 | | +----------- 6th hour of the day.
	 *                 | +------------- top of the hour (minutes = 0).
	 *                 +--------------- top of the minute (seconds = 0).
	 */
	public void cleanUpAPIEvents() {
		logger.debug("Starting API repo cleanup at "
				+ new Date(System.currentTimeMillis()));

		List<APIEvent> events = apiRepo.getAll(null);
		logger.debug("Events size: " + events.size());

		// if its larger than the max value
		if (events.size() > max) {
			int size = events.size();

			for (int i = 0; i < size - max; i++) {
				apiRepo.delete(events.get(i).getId());
			}
		}

		logger.debug("API repo cleanup done at new size is "
				+ (events.size() - (events.size() - max)) + " "
				+ new Date(System.currentTimeMillis()));
	}

	/**
	 * Sends out a reminder to the students that have failed to 
	 * return the consoles.
	 */
	@Scheduled(cron = "0 0 2 * * *")
	public void checkIfLoanisExpired() {
		GregorianCalendar now = new GregorianCalendar();
		List<Loan> loans = loanRepo.getAll(null);
	

		for (Loan l : loans) {
			String subject = "Påminnelse om å retuner lån fra Battlestation";
			StringBuffer greeting = new StringBuffer("Hei");

			StringBuffer body = new StringBuffer(
					"Dette er en påminnelse om at utstyert du har lånt er på overtid.\n"
							+ "Leveres tilbake snarest\n");

			StringBuffer borrowed = new StringBuffer("Utstyert du har lånt er:");
			String from = "nithscommunity@gmail.com";
			
			if (now.compareTo(l.getEndTime()) > 0) {
				if (l.getStudent() != null) {

					// add console name
					for (Console c : l.getConsoles()) {
						borrowed.append(c.getName() + "\n");
					}

					// add student name
					greeting.append((l.getStudent().getFirstName() != null ? " "+l
							.getStudent().getFirstName() : " student")
							+ "!\n");
				}
				
				String message = greeting + "" + body + "" + borrowed;
				mailSender.composeAndSend(l.getStudent().getEmail(), from,subject, message);
				
			}// end compare to
		}// end loans
	}

}