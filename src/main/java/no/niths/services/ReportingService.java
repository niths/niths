package no.niths.services;

import java.util.Date;
import java.util.List;

import no.niths.domain.APIEvent;
import no.niths.infrastructure.interfaces.APIEventRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReportingService {
	private static final Logger logger = LoggerFactory
			.getLogger(ReportingService.class);

	@Autowired
	private APIEventRepository apiRepo;

	private final int max = 5;

	// 0 0 6 6 9 ? 
	// | | | | | | 
	// | | | | | | 
	// | | | | | +----- any day of the week.
	// | | | | +------- 9th month (September).
	// | | | +--------- 6th day of the month.
	// | | +----------- 6th hour of the day.
	// | +------------- Top of the hour (minutes = 0).
	// +--------------- Top of the minute (seconds = 0).

	// @Scheduled(cron = "0 23 16 * * *")
	@Scheduled(fixedRate = 60000)
	public void helloWorld() {
		logger.debug("Starting API repo cleanup at "
				+ new Date(System.currentTimeMillis()));

		List<APIEvent> events = apiRepo.getAll(null);
		logger.debug("Events size: " + events.size());

		// if its larger than max
		if (events.size() > max) {
			int size = events.size();
			
			for (int i = 0; i < Math.abs(size-max); i++) {
				apiRepo.delete(events.get(i).getId());
			}
		}

		logger.debug("API repo cleanup done at new size is " + (events.size()-(events.size()-max))
				+ " " + new Date(System.currentTimeMillis()));
	}

}