package com.p.boot.interview.mgmt.rest.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.p.boot.interview.mgmt.pojo.CategoryDTO;
import com.p.boot.interview.mgmt.repository.CategoryRepository;

@RestController
@RequestMapping(path = "categories")
public class CategoryResource {
	
	
	@Autowired
	@Qualifier("namedParameterJdbcCategoryRepository")  // Test NamedParameterJdbcTemplate
    private CategoryRepository categoryRepository;

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(CategoryResource.class);
	
	@GetMapping(path="/", produces = "application/json")
	public List<CategoryDTO> getAllCategories() {
		
		return categoryRepository.findAll();
		
	}
	
	

}
