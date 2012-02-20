/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package no.niths.application.rest.auth;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.legacyprofile.LegacyGoogleProfile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("google")
public class HomeController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

//	private final Google google;
//	
//	@Autowired
//	public HomeController(Google google) {
//		this.google = google;
//	}
	
//	@ExceptionHandler(ExpiredAuthorizationException.class)
//	public void handleExpiredToken() {
//		throw new RuntimeException("Gjør meg om til et exception");
//	}

	@RequestMapping(value="/", method=GET)
	@ResponseBody
	public String home() {
		Google google = new GoogleTemplate("1/eX9p_8xMmFzwh8MkawUp92BvhNHGeiwt4Any4qVI_IQ");
		LegacyGoogleProfile profile = google.userOperations().getUserProfile();
		logger.debug("Er jeg innlogget? : " + profile.getEmail());
		return profile.getEmail();
	}
	
}