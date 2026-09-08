package com.emp.employeeopenapidoc.config; // config package

import io.swagger.v3.oas.models.OpenAPI; // OpenAPI document object
import io.swagger.v3.oas.models.info.Info; // title / version / description
import io.swagger.v3.oas.models.servers.Server; // base URL in Swagger
import org.springframework.context.annotation.Bean; // register a Spring bean
import org.springframework.context.annotation.Configuration; // mark as config class

import java.util.List; // list of servers

@Configuration // Spring loads this at startup
public class OpenApiConfig { // Swagger metadata

	@Bean // Spring keeps this OpenAPI object
	public OpenAPI employeeOpenApi() { // build the spec header
		return new OpenAPI() // new OpenAPI document
				.info(new Info() // API info block
						.title("Employee API") // name in Swagger UI
						.version("1.0.0") // API version
						.description("CRUD APIs for employees. Try them in Swagger UI.")) // short help text
				.servers(List.of(new Server().url("http://localhost:8080").description("Local"))); // Try-it-out host
	} // end employeeOpenApi
} // end class
