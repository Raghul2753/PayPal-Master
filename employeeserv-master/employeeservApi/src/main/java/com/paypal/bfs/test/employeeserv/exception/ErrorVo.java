package com.paypal.bfs.test.employeeserv.exception;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "error_code",
    "error_message"
})
public class ErrorVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1201465886131280458L;
	 @JsonProperty("error_code")
	private int errorCode;
	 @JsonProperty("error_message")
	private String errorMessage;
	public ErrorVo(int errorCode,String errorMessage)
	{
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	
}
