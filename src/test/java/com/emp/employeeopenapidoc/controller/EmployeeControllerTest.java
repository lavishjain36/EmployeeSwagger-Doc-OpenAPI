package com.emp.employeeopenapidoc.controller; // test lives next to controller package

import com.emp.employeeopenapidoc.repository.EmployeeRepository; // wipe table
import org.junit.jupiter.api.BeforeEach; // before each test
import org.junit.jupiter.api.Test; // mark a test
import org.springframework.beans.factory.annotation.Autowired; // inject beans
import org.springframework.boot.test.context.SpringBootTest; // start full app
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc; // fake HTTP
import org.springframework.http.MediaType; // application/json
import org.springframework.test.context.ActiveProfiles; // pick test properties
import org.springframework.test.web.servlet.MockMvc; // call APIs in test

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; // GET
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; // POST
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath; // check JSON
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; // check status

@SpringBootTest // boot Spring
@AutoConfigureMockMvc // give us mockMvc
@ActiveProfiles("test") // H2, not MySQL
class EmployeeControllerTest { // HTTP tests

	@Autowired
	private MockMvc mockMvc; // call controller without a real port

	@Autowired
	private EmployeeRepository employeeRepository; // used to reset data

	@BeforeEach
	void reset() { // before every test
		employeeRepository.deleteAll(); // DELETE all rows
	} // end reset

	@Test
	void createAndGet() throws Exception { // create then get then missing
		String body = mockMvc.perform(post("/api/v1/employees") // POST create
						.contentType(MediaType.APPLICATION_JSON) // JSON
						.content("{\"firstName\":\"Priya\",\"lastName\":\"Sharma\",\"email\":\"priya@example.com\",\"department\":\"Engineering\",\"jobTitle\":\"Engineer\"}")) // body
				.andExpect(status().isCreated()) // expect 201
				.andExpect(jsonPath("$.firstName").value("Priya")) // expect name
				.andReturn() // stop and read response
				.getResponse() // HTTP response
				.getContentAsString(); // JSON text

		String id = body.replaceAll(".*\"id\":(\\d+).*", "$1"); // pull id from JSON
		mockMvc.perform(get("/api/v1/employees/" + id)).andExpect(status().isOk()); // GET found → 200
		mockMvc.perform(get("/api/v1/employees/99999")).andExpect(status().isNotFound()); // GET missing → 404
	} // end createAndGet
} // end class
