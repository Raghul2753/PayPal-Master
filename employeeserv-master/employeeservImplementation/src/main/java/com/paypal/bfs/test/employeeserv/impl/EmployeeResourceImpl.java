package com.paypal.bfs.test.employeeserv.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.bfs.test.employeeserv.api.CustomException;
import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

	@Autowired
	@Qualifier("empService")
	EmployeeService empService;

	@Override
	public ResponseEntity<Employee> employeeGetById(String id) throws NumberFormatException, CustomException {
		return new ResponseEntity<>(empService.getEmployeeById(Integer.parseInt(id)), HttpStatus.OK);
	}

	@Override
	public void deleteEmployee(String id) throws NumberFormatException, CustomException {
		empService.deleteEmployeeById(Integer.parseInt(id));

	}

	@Override
	public ResponseEntity<Employee> saveEmployee(@Valid Employee employee) throws NumberFormatException, CustomException {
		return ResponseEntity.ok(empService.saveEmployee(employee));
	}

	@Override
	public ResponseEntity<Employee> updateEmployee(String id, @RequestBody Employee employee) throws NumberFormatException, CustomException {
		return ResponseEntity.ok(empService.updateEmployee(Integer.parseInt(id), employee));
	}

	@Override
	public ResponseEntity<List<Employee>> getAllEmployee() {
		return ResponseEntity.ok(empService.getAllEmployees());
	}
}
