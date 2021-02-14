package  com.paypal.bfs.test.employeeserv.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.employeeserv.EmployeeservApplication;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;

/**
 * @author avrag
 *
 *         Integration tests to check all apis were working
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeservApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IntegrationTests {

	@LocalServerPort
	private int port;

	@Autowired
	ObjectMapper mapper;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	/**
	 * Method to test the POST method and it will be the base for below test records
	 * 
	 * @throws Exception
	 */
	@Test
	public void myATest() throws Exception {
		Employee emp1 = new Employee();
		emp1.setId(2);
		emp1.setDateOfBirth(new Date(694137600000L));
		emp1.setFirstName("Raghul");
		emp1.setLastName("Raghul");
		Address address = new Address();
		address.setLine1("xxxxx");
		address.setLine2("yyyyy");
		address.setCity("zzzzz");
		address.setState("aaaaaaa");
		address.setCountry("bbbbbbb");
		address.setZipCode(641659);
		address.setAdditionalProperty("addId", 1);
		emp1.setAddress(address);

		HttpEntity<Employee> entity = new HttpEntity<Employee>(emp1, headers);

		ResponseEntity<Employee> response = restTemplate.exchange(createURLWithPort("v1/bfs/employees"),
				HttpMethod.POST, entity, Employee.class);
		assertTrue(response.hasBody());
		assertEquals("xxxxx", response.getBody().getAddress().getLine1());
		assertEquals(new Date(694137600000L), response.getBody().getDateOfBirth());

		assertEquals("aaaaaaa", response.getBody().getAddress().getState());

	}

	/**
	 * Method to get all the records which were saved earlier
	 * 
	 * @throws Exception
	 */
	@Test
	public void myBTest() throws Exception {

		Employee emp1 = new Employee();
		emp1.setId(2);
		emp1.setDateOfBirth(new Date(694137600000L));
		emp1.setFirstName("Raghul");
		emp1.setLastName("Raghul");
		Address address = new Address();
		address.setLine1("xxxxx");
		address.setLine2("yyyyy");
		address.setCity("zzzzz");
		address.setState("aaaaaaa");
		address.setCountry("bbbbbbb");
		address.setZipCode(641659);
		address.setAdditionalProperty("addId", 1);
		emp1.setAddress(address);

		HttpEntity<Employee> entity = new HttpEntity<Employee>(emp1, headers);

		ResponseEntity<List> response = restTemplate.exchange(createURLWithPort("v1/bfs/employees"), HttpMethod.GET,
				entity, List.class);
		assertTrue(response.hasBody());
		List<LinkedHashMap<String, Object>> empList = (List<LinkedHashMap<String, Object>>) response.getBody();
		LinkedHashMap<String, Object> emp = empList.get(0);
		assertEquals(empList.size(), 1);
		assertEquals("Raghul", emp.get("first_name"));

	}

	/**
	 * Method to update a record in the database
	 * 
	 * @throws Exception
	 */
	@Test
	public void myCTest() throws Exception {
		Employee emp1 = new Employee();
		emp1.setId(2);
		emp1.setDateOfBirth(new Date(694137600000L));
		emp1.setFirstName("Raghul12");
		emp1.setLastName("Raghul");
		Address address = new Address();
		address.setLine1("xxxxx");
		address.setLine2("yyyyy");
		address.setCity("zzzzz");
		address.setState("aaaaaaa");
		address.setCountry("bbbbbbb");
		address.setZipCode(641659);
		address.setAdditionalProperty("addId", 1);
		emp1.setAddress(address);

		HttpEntity<Employee> entity = new HttpEntity<Employee>(emp1, headers);

		ResponseEntity<Employee> response = restTemplate.exchange(createURLWithPort("v1/bfs/employees/2"),
				HttpMethod.PUT, entity, Employee.class);
		assertTrue(response.hasBody());

		assertEquals("Raghul12", response.getBody().getFirstName());

	}

	/**
	 * Method to get the updated record from the data base
	 * 
	 * @throws Exception
	 */
	@Test
	public void myDTest() throws Exception {
		HttpEntity<Employee> entity = new HttpEntity<Employee>(null, headers);

		ResponseEntity<Employee> response = restTemplate.exchange(createURLWithPort("v1/bfs/employees/2"),
				HttpMethod.GET, entity, Employee.class);
		assertTrue(response.hasBody());
		assertEquals("Raghul12", response.getBody().getFirstName());

	}

	/**
	 * Method to delete a record from the data base
	 * 
	 * @throws Exception
	 */
	@Test
	public void myETest() throws Exception {
		HttpEntity<Employee> entity = new HttpEntity<Employee>(null, headers);

		ResponseEntity<Employee> response = restTemplate.exchange(createURLWithPort("v1/bfs/employees/2"),
				HttpMethod.DELETE, entity, Employee.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	/**
	 * Method to check whether data is deleted from db
	 * 
	 * @throws Exception
	 */
	@Test
	public void myFTest() throws Exception {
		HttpEntity<Employee> entity = new HttpEntity<Employee>(null, headers);

		ResponseEntity<Employee> response = restTemplate.exchange(createURLWithPort("v1/bfs/employees/2"),
				HttpMethod.GET, entity, Employee.class);
		assertTrue(response.hasBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

	}

}
