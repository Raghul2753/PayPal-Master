package com.paypal.bfs.test.employeeserv.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.employeeserv.api.CustomException;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;

@Service("empService")
public class EmployeeServiceImpl implements EmployeeService {

	Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	EmployeeRepository empRepo;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	DozerBeanMapper dozerMapper;

	@Override
	public Employee getEmployeeById(Integer id) throws CustomException {
		logger.debug("Getting the employee by id {}", id);
		EmployeeEntity empEnt = null;
		try {
			empEnt = empRepo.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new CustomException("No data available for the id", 404);
		}
		if (empEnt == null) {
			throw new CustomException("No data available for the id", 404);
		}
		Employee emp = mapper.convertValue(empEnt, Employee.class);
		logger.debug("Getting the employee by id {} is success", id);

		return emp;
	}

	@Override
	public List<Employee> getAllEmployees() {
		logger.debug("Getting all the employees");
		List<EmployeeEntity> empEntList = (List<EmployeeEntity>) empRepo.findAll();
		List<Employee> employeeList = new ArrayList<Employee>();
		try {
			for (EmployeeEntity empEnt : empEntList) {
				Employee emp = mapper.convertValue(empEnt, Employee.class);
				employeeList.add(emp);
			}
			logger.debug("Getting all employees the is Success");
		} catch (Exception e) {
			System.out.println(e);
		}
		return employeeList;
	}

	@Override
	public Employee saveEmployee(Employee emp) throws CustomException {
		logger.debug("Persisting the employee");
		if (emp.getId() != null) {
			if (empRepo.existsById(emp.getId())) {
				throw new CustomException("Employee with id : " + emp.getId() + " already exists", 409);
			}
		}
		EmployeeEntity empEnt = mapper.convertValue(emp, EmployeeEntity.class);
		empEnt = empRepo.save(empEnt);
		emp = mapper.convertValue(empEnt, Employee.class);
		logger.debug("Persisting the employee is success");

		return emp;
	}

	@Override
	public void deleteEmployeeById(Integer id) throws CustomException {
		logger.debug("Deleting the employee by id {}", id);
		try {
			empRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new CustomException("No data available for the id", 404);
		}
		logger.debug("Deleting the employee by id {} is success", id);

	}

	@Override
	public Employee updateEmployee(Integer id, Employee emp) throws CustomException {
		logger.debug("updating the employee by id {}", id);
		EmployeeEntity empEnt = null;
		try {
			empEnt = empRepo.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new CustomException("No data available for the id", 404);
		}
		if (empEnt == null) {
			throw new CustomException("No data available for the id", 404);
		}
		dozerMapper.map(emp, empEnt);
		empEnt.setId(id);
		empEnt = empRepo.save(empEnt);
		emp = mapper.convertValue(empEnt, Employee.class);
		logger.debug("Updating the employee by id {} is success", id);

		return emp;

	}

}
