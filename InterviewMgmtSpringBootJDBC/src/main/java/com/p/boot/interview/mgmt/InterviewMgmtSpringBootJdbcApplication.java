package com.p.boot.interview.mgmt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

import com.p.boot.interview.mgmt.repository.CategoryRepository;

@SpringBootApplication
@ComponentScan("com.p.boot.interview.mgmt")
public class InterviewMgmtSpringBootJdbcApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(InterviewMgmtSpringBootJdbcApplication.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("namedParameterJdbcCategoryRepository") // Test NamedParameterJdbcTemplate
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(InterviewMgmtSpringBootJdbcApplication.class, args);
	}

	public void run(String... args) {

		log.info("StartApplication...");
		// startCustomerApp();
	}

	// private void startCustomerApp() {
	//
	// log.info("[FIND_ALL]");
	// log.info("all categories data : {}", categoryRepository.findAll());
	//
	// }

}
