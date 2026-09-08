package com.emp.employeeopenapidoc.config; // settings folder

import io.swagger.v3.oas.models.OpenAPI; // the Swagger document
import io.swagger.v3.oas.models.info.Info; // name and description of the API
import io.swagger.v3.oas.models.servers.Server; // address where the API runs
import org.springframework.context.annotation.Bean; // give this object to Spring
import org.springframework.context.annotation.Configuration; // this file is settings, not an API

import java.util.List; // a list

@Configuration // load these settings when the app starts
public class OpenApiConfig { // Swagger page heading and address

	@Bean // keep this so Swagger UI can use it
	public OpenAPI employeeOpenApi() { // build what you see at the top of Swagger
		return new OpenAPI() // start a new Swagger document
				.info(new Info() // name block
						.title("Employee API") // big title on the Swagger page
						.version("1.0.0") // version number
						.description("CRUD APIs for employees. Try them in Swagger UI.")) // short note for learners
				.servers(List.of(new Server().url("http://localhost:8080").description("Local"))); // "try it" uses this computer
	} // done building Swagger info
} // end of file
