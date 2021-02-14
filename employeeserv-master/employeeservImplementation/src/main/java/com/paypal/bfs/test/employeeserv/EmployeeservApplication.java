package com.paypal.bfs.test.employeeserv;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class EmployeeservApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmployeeservApplication.class, args);
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public DozerBeanMapper mapper() {
		return new DozerBeanMapper();
	}
	
	

}