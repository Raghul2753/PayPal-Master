package com.paypal.bfs.test.employeeserv.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.exception.CustomException;

/**
 * Interface for employee resource operations.
 */
public interface EmployeeResource {

	/**
	 * Retrieves the {@link Employee} resource by id.
	 *
	 * @param id employee id.
	 * @return {@link Employee} resource.
	 * @throws CustomException
	 * @throws NumberFormatException
	 */
	@GetMapping("/v1/bfs/employees/{id}")
	ResponseEntity<Employee> employeeGetById(@PathVariable("id") String id)
			throws NumberFormatException, CustomException;

	/**
	 * Retrieves all the employees
	 * @return
	 */
	@GetMapping("/v1/bfs/employees")
	ResponseEntity<List<Employee>> getAllEmployee();

	/**
	 * Delete employee by id
	 * @param id
	 * @throws NumberFormatException
	 * @throws CustomException
	 */
	@DeleteMapping("/v1/bfs/employees/{id}")
	void deleteEmployee(@PathVariable("id") String id) throws NumberFormatException, CustomException;

	/**
	 * Save employee
	 * @param employee
	 * @return
	 * @throws NumberFormatException
	 * @throws CustomException
	 */
	@PostMapping("/v1/bfs/employees")
	ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee)throws NumberFormatException, CustomException;

	/**
	 * Update employee by id
	 * @param id
	 * @param employee
	 * @return
	 * @throws NumberFormatException
	 * @throws CustomException
	 */
	@PutMapping("/v1/bfs/employees/{id}")
	ResponseEntity<Employee> updateEmployee(@PathVariable("id") String id, @RequestBody Employee employee) throws NumberFormatException, CustomException;

}
