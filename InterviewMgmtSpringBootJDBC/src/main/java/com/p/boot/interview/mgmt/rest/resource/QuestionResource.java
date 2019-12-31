package com.p.boot.interview.mgmt.rest.resource;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.p.boot.interview.mgmt.pojo.QuestionDTO;
import com.p.boot.interview.mgmt.repository.QuestionRepository;

@RestController
@RequestMapping(path = "categories/{linkedCategoryID}/questions")
public class QuestionResource {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(QuestionResource.class);
	
	static {
		logger.debug("Implementation yet to be written!");
	}
	
	@Autowired
	@Qualifier("namedParameterJdbcQuestionRepository") // Test NamedParameterJdbcTemplate
	private QuestionRepository questionRepository;
	
	@GetMapping(path = "/", produces = "application/json")	
	public List<QuestionDTO> getAllQuestions(@PathVariable("linkedCategoryID") int linkedCategoryID) {
		
		return null;
	}

}
