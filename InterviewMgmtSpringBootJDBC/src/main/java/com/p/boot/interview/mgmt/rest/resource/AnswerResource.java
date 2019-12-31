package com.p.boot.interview.mgmt.rest.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "categories/{linkedCategoryID}/questions/{linkedQuestionID}/answers")
public class AnswerResource {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(AnswerResource.class);
	
	static {
		logger.debug("Implementation yet to be written!");
	}

}
