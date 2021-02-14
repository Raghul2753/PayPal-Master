package com.paypal.bfs.test.employeeserv.service;

import java.util.List;

import com.paypal.bfs.test.employeeserv.api.CustomException;
import com.paypal.bfs.test.employeeserv.api.model.Employee;

public interface EmployeeService {

	public Employee getEmployeeById(Integer id) throws CustomException;

	public List<Employee> getAllEmployees();

	public Employee saveEmployee(Employee emp) throws CustomException;
	
	public void deleteEmployeeById(Integer id) throws CustomException;
	
	public Employee updateEmployee(Integer id, Employee e) throws CustomException;

}
