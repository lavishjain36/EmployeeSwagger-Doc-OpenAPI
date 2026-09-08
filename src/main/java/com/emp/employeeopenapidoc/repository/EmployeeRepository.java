package com.emp.employeeopenapidoc.repository; // folder that talks to the database

import com.emp.employeeopenapidoc.model.Employee; // we store Employee records
import org.springframework.data.jpa.repository.JpaRepository; // ready-made save / find / delete

public interface EmployeeRepository extends JpaRepository<Employee, Long> { // Employee, number is Long
	// already included: save, find everyone, find by number, delete by number

	boolean existsByEmailIgnoreCase(String email); // is this email already taken? (when adding)

	boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id); // is this email used by someone else? (when editing)
} // end of file
