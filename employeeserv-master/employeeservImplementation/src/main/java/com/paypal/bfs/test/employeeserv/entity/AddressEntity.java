
package com.paypal.bfs.test.employeeserv.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value = { "employee" })
public class AddressEntity {

	@Id
	@GeneratedValue
	@JsonIgnore
	@JsonProperty("add_id")
	private Integer addId;
	
	@JsonProperty("line1")
	private String line1;
	
	@JsonProperty("line2")
	private String line2;
	
	@JsonProperty("city")
	private String city;
	
	@JsonProperty("state")
	private String state;
	
	@JsonProperty("country")
	private String country;
	
	@JsonProperty("zip_code")
	private Integer zipCode;
	
	@OneToMany(mappedBy = "address")
	@JsonIgnore
	private List<EmployeeEntity> employee;
	public AddressEntity(int id, String line1, String line2, String city, String state, String country, int zipCode) {
		this.addId =id;
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
	}

}
