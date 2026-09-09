package com.emp.employeeopenapidoc.controller; // same folder as the web class

import com.emp.employeeopenapidoc.repository.EmployeeRepository; // used to empty the table
import org.junit.jupiter.api.BeforeEach; // run before each check
import org.junit.jupiter.api.Test; // marks a check
import org.springframework.beans.factory.annotation.Autowired; // Spring fills this field
import org.springframework.boot.test.context.SpringBootTest; // start the whole app
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc; // fake HTTP client
import org.springframework.http.MediaType; // we send JSON
import org.springframework.test.context.ActiveProfiles; // pick test settings
import org.springframework.test.web.servlet.MockMvc; // call URLs without a browser

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; // GET request
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; // POST request
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; // check created / not found

@SpringBootTest // boot the app for this class
@AutoConfigureMockMvc // give us mockMvc
@ActiveProfiles("test") // use H2, not MySQL
class EmployeeControllerTest { // checks real URLs

	private static final String AJAY = "{" // JSON body for add
			+ "\"firstName\":\"Ajay\"," // first name
			+ "\"lastName\":\"Sharma\"," // last name
			+ "\"email\":\"ajay@gmail.com\"," // email
			+ "\"department\":\"Engineering\"," // department
			+ "\"jobTitle\":\"Engineer\"" // job title
			+ "}"; // end JSON

	@Autowired // Spring provides this
	MockMvc mockMvc; // used to call POST/GET

	@Autowired // Spring provides this
	EmployeeRepository employeeRepository; // used to clear rows

	@BeforeEach // before every @Test
	void clearTable() {
		employeeRepository.deleteAll(); // start with an empty table
	}

	@Test // check 1: add works
	void addPersonWorks() throws Exception {
		mockMvc.perform(post("/api/v1/employees") // call add URL
						.contentType(MediaType.APPLICATION_JSON) // body is JSON
						.content(AJAY)) // send Ajay
				.andExpect(status().isCreated()); // must be created
	}

	@Test // check 2: unknown id fails
	void unknownPersonFails() throws Exception {
		mockMvc.perform(get("/api/v1/employees/99999")) // ask for a fake number
				.andExpect(status().isNotFound()); // must be not found
	}
}
