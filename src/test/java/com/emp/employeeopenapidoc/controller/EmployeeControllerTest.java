package com.emp.employeeopenapidoc.controller; // same folder as the web class

import com.emp.employeeopenapidoc.repository.EmployeeRepository; // used to clear people
import org.junit.jupiter.api.BeforeEach; // run before each check
import org.junit.jupiter.api.Test; // this method is a check
import org.springframework.beans.factory.annotation.Autowired; // fill these fields for us
import org.springframework.boot.test.context.SpringBootTest; // start the app for the check
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc; // fake browser
import org.springframework.http.MediaType; // we send JSON
import org.springframework.test.context.ActiveProfiles; // use the test settings
import org.springframework.test.web.servlet.MockMvc; // pretend to call the API

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; // pretend GET
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; // pretend POST
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath; // check a field
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; // check success or not

@SpringBootTest // start the app
@AutoConfigureMockMvc // give us the fake browser
@ActiveProfiles("test") // use the tiny test database, not MySQL
class EmployeeControllerTest { // checks the web calls

	@Autowired
	private MockMvc mockMvc; // fake browser

	@Autowired
	private EmployeeRepository employeeRepository; // so we can empty the table

	@BeforeEach
	void reset() { // before every check
		employeeRepository.deleteAll(); // remove all people
	} // ready for a clean check

	@Test
	void createAndGet() throws Exception { // add someone, fetch them, then try a missing number
		String body = mockMvc.perform(post("/api/v1/employees") // call add
						.contentType(MediaType.APPLICATION_JSON) // we are sending JSON
						.content("{\"firstName\":\"Priya\",\"lastName\":\"Sharma\",\"email\":\"priya@example.com\",\"department\":\"Engineering\",\"jobTitle\":\"Engineer\"}")) // Priya's details
				.andExpect(status().isCreated()) // should say created
				.andExpect(jsonPath("$.firstName").value("Priya")) // first name should be Priya
				.andReturn() // stop and read the reply
				.getResponse() // the reply
				.getContentAsString(); // reply as text

		String id = body.replaceAll(".*\"id\":(\\d+).*", "$1"); // pick the new number out of the reply
		mockMvc.perform(get("/api/v1/employees/" + id)).andExpect(status().isOk()); // fetch that person — should work
		mockMvc.perform(get("/api/v1/employees/99999")).andExpect(status().isNotFound()); // fetch a fake number — should fail
	} // check finished
} // end of file
