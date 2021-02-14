package com.paypal.bfs.test.employeeserv.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.employeeserv.api.CustomException;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.AddressEntity;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.service.impl.EmployeeServiceImpl;

/**
 * @author avrag Class to test all the service layer in the project
 */
@RunWith(MockitoJUnitRunner.Silent.class)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@SpringBootTest
public class ServiceLayerTests {

	@Mock
	ObjectMapper mapper;

	@Mock
	DozerBeanMapper dozerMapper;

	@Mock
	EmployeeRepository empRepo;

	@InjectMocks
	EmployeeServiceImpl empService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Method to test get all the employees in data base
	 */
	@Test
	public void getAllEmployeesTest() {
		List<EmployeeEntity> list = new ArrayList<EmployeeEntity>();
		EmployeeEntity empOne = new EmployeeEntity(1, "John", "John", new Date(694137600000L), new AddressEntity(1,
				"sdfsdf", "sdfsdfsd", "adfsdfsd", "sadfsdfs", "adsfsdf", 641659, new ArrayList<EmployeeEntity>()));
		EmployeeEntity emptwo = new EmployeeEntity(2, "John", "John", new Date(694137600000L), new AddressEntity(1,
				"sdfsdf", "sdfsdfsd", "adfsdfsd", "sadfsdfs", "adsfsdf", 641659, new ArrayList<EmployeeEntity>()));
		EmployeeEntity empthree = new EmployeeEntity(3, "John", "John", new Date(694137600000L), new AddressEntity(1,
				"sdfsdf", "sdfsdfsd", "adfsdfsd", "sadfsdfs", "adsfsdf", 641659, new ArrayList<EmployeeEntity>()));
		Employee emp1 = new Employee();
		emp1.setId(1);
		emp1.setDateOfBirth(new Date(694137600000L));
		emp1.setFirstName("adsfsdf");
		emp1.setLastName("asdfsdfsd");
		Address address = new Address();
		address.setLine1("dsdf");
		address.setLine2("aefsdfs");
		address.setCity("adfsdf");
		address.setState("dsfsdfsd");
		address.setCountry("asddsf");
		address.setZipCode(641659);
		emp1.setAddress(address);
		list.add(empOne);
		list.add(emptwo);
		list.add(empthree);
		List<Employee> listEmp = new ArrayList<Employee>();
		listEmp.add(emp1);
		listEmp.add(emp1);
		listEmp.add(emp1);
		when(empRepo.findAll()).thenReturn(list);

		when(mapper.convertValue(empOne, Employee.class)).thenReturn(emp1);
		when(mapper.convertValue(emptwo, Employee.class)).thenReturn(emp1);

		when(mapper.convertValue(empthree, Employee.class)).thenReturn(emp1);

		// test
		List<Employee> empList = empService.getAllEmployees();

		assertEquals(3, empList.size());
		assertEquals(empList, listEmp);
		verify(empRepo, times(1)).findAll();

	}

	/**
	 * Method to save the data into db
	 * 
	 * @throws CustomException
	 */
	@Test
	public void saveEmployeeTest() throws CustomException {
		EmployeeEntity empOne = new EmployeeEntity(1, "John", "John", new Date(694137600000L), new AddressEntity(1,
				"sdfsdf", "sdfsdfsd", "adfsdfsd", "sadfsdfs", "adsfsdf", 641659, new ArrayList<EmployeeEntity>()));
		Employee emp1 = new Employee();
		emp1.setId(1);
		emp1.setDateOfBirth(new Date(694137600000L));
		emp1.setFirstName("adsfsdf");
		emp1.setLastName("asdfsdfsd");
		Address address = new Address();
		address.setLine1("dsdf");
		address.setLine2("aefsdfs");
		address.setCity("adfsdf");
		address.setState("dsfsdfsd");
		address.setCountry("asddsf");
		address.setZipCode(641659);
		emp1.setAddress(address);
		when(mapper.convertValue(emp1, EmployeeEntity.class)).thenReturn(empOne);

		when(empRepo.existsById(Mockito.eq(empOne.getId()))).thenReturn(false);
		when(empRepo.save(Mockito.eq(empOne))).thenReturn(empOne);
		when(mapper.convertValue(empOne, Employee.class)).thenReturn(emp1);
		assertEquals(emp1, empService.saveEmployee((emp1)));
		verify(empRepo, times(1)).save(Mockito.eq(empOne));

	}

	/**
	 * 
	 * Method to get the employee by id
	 * 
	 * @throws CustomException
	 */
	@Test
	public void getEmpById() throws CustomException {

		EmployeeEntity empOne = new EmployeeEntity(1, "John", "John", new Date(694137600000L), new AddressEntity(1,
				"sdfsdf", "sdfsdfsd", "adfsdfsd", "sadfsdfs", "adsfsdf", 641659, new ArrayList<EmployeeEntity>()));

		Employee emp1 = new Employee();
		emp1.setId(1);
		emp1.setDateOfBirth(new Date(694137600000L));
		emp1.setFirstName("adsfsdf");
		emp1.setLastName("asdfsdfsd");
		Address address = new Address();
		address.setLine1("dsdf");
		address.setLine2("aefsdfs");
		address.setCity("adfsdf");
		address.setState("dsfsdfsd");
		address.setCountry("asddsf");
		address.setZipCode(641659);
		emp1.setAddress(address);

		when(empRepo.findById(emp1.getId())).thenReturn(Optional.of(empOne));

		when(mapper.convertValue(empOne, Employee.class)).thenReturn(emp1);
		// test
		Employee employee = empService.getEmployeeById(emp1.getId());

		assertEquals(emp1, employee);
		verify(empRepo, times(1)).findById(emp1.getId());
	}

	/**
	 * Method to update the employee in db
	 * 
	 * @throws CustomException
	 */
	@Test
	public void updateEmployee() throws CustomException {
		EmployeeEntity empOne = new EmployeeEntity(1, "John", "John", new Date(694137600000L), new AddressEntity(1,
				"sdfsdf", "sdfsdfsd", "adfsdfsd", "sadfsdfs", "adsfsdf", 641659, new ArrayList<EmployeeEntity>()));
		Employee emp1 = new Employee();
		emp1.setId(1);
		emp1.setDateOfBirth(new Date(694137600000L));
		emp1.setFirstName("adsfsdf");
		emp1.setLastName("asdfsdfsd");
		Address address = new Address();
		address.setLine1("dsdf");
		address.setLine2("aefsdfs");
		address.setCity("adfsdf");
		address.setState("dsfsdfsd");
		address.setCountry("asddsf");
		address.setZipCode(641659);
		emp1.setAddress(address);
		// when(mapper.convertValue(emp1, EmployeeEntity.class)).thenReturn(empOne);

		when(empRepo.findById(Mockito.eq(empOne.getId()))).thenReturn(Optional.of(empOne));
		doNothing().when(dozerMapper).map(emp1, empOne);

		when(empRepo.save(Mockito.eq(empOne))).thenReturn(empOne);
		when(mapper.convertValue(empOne, Employee.class)).thenReturn(emp1);
		assertEquals(emp1, empService.updateEmployee(empOne.getId(), emp1));
		verify(empRepo, times(1)).save(Mockito.eq(empOne));

	}

}
