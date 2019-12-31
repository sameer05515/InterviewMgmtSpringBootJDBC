package com.p.boot.interview.mgmt.rest.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.p.boot.interview.mgmt.pojo.CategoryDTO;
import com.p.boot.interview.mgmt.repository.CategoryRepository;

@RestController
@RequestMapping(path = "categories")
public class CategoryResource {

	@Autowired
	@Qualifier("namedParameterJdbcCategoryRepository") // Test NamedParameterJdbcTemplate
	private CategoryRepository categoryRepository;

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(CategoryResource.class);
	
	static {
		logger.debug("Implementation yet to be written!");
	}

	@GetMapping(path = "/", produces = "application/json")
	// @TrackTime
	public List<CategoryDTO> getAllCategories() {

		List<CategoryDTO> data = categoryRepository.findAll();
		// logger.info("inside getAllCategories "+data);
		return data;

	}

	@GetMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
	// @TrackTime
	public CategoryDTO getCategory(@PathVariable(name = "id", required = true) int id) throws Exception {
		CategoryDTO objCategoryDTO = categoryRepository.findById(id);
		if (objCategoryDTO != null) {
			return objCategoryDTO;
		} else {
			throw new Exception("No data found for id :" + id);
		}
	}

	@PostMapping(path = "/", produces = "application/json", consumes = "application/json")
	public int saveCategory(@RequestBody CategoryDTO objCategoryDTO) {

		return categoryRepository.save(objCategoryDTO);
	}

	@PutMapping(path = "/", produces = "application/json", consumes = "application/json")
	public int updateCategory(@RequestBody CategoryDTO objCategoryDTO) {

		return categoryRepository.update(objCategoryDTO);
	}

}
