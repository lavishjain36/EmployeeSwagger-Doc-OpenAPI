package com.emp.employeeopenapidoc.repository; // repository package

import com.emp.employeeopenapidoc.model.Employee; // entity this repo stores
import org.springframework.data.jpa.repository.JpaRepository; // built-in CRUD

public interface EmployeeRepository extends JpaRepository<Employee, Long> { // Employee + Long id
	// JpaRepository already has: save, findAll, findById, deleteById

	boolean existsByEmailIgnoreCase(String email); // true if email already used (create)

	boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id); // true if another row has this email (update)
} // end interface
