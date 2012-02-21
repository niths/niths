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

import no.niths.common.ValidationHelper;
import no.niths.infrastructure.AuthenticationServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@Controller
@RequestMapping("google")
public class LoginController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	
	@Autowired
	private AuthenticationServiceImpl service;

	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public String home(@RequestParam String token) {
		ValidationHelper.isObjectNull(token);
		
		String response = service.login(token);
		logger.debug(response);
		//logger.debug("USER SIGNED IN: " + SecurityContext.getCurrentUser().getId());
		return response;
	}
	
	
	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "You do not have access")
	public void nonAccess() {
		logger.info("User tried to log in, but failed");
	}
	
}