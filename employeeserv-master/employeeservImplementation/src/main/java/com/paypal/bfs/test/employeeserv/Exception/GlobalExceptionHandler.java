package com.paypal.bfs.test.employeeserv.Exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.paypal.bfs.test.employeeserv.api.CustomException;
import com.paypal.bfs.test.employeeserv.exception.ErrorVo;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(CustomException.class)
	@ResponseBody
	public ResponseEntity<ErrorVo> resourceNotFoundException(CustomException ex) {
		ErrorVo errorDetails = new ErrorVo(ex.getStatusCode(), ex.getMessage());
		return new ResponseEntity<ErrorVo>(errorDetails, HttpStatus.valueOf(ex.getStatusCode()));
	}

	@ExceptionHandler(NumberFormatException.class)
	@ResponseBody
	public ResponseEntity<ErrorVo> numberFormatException(NumberFormatException ex) {
		ErrorVo errorDetails = new ErrorVo(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage());
		return new ResponseEntity<ErrorVo>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<?> formatException(InvalidFormatException ex) {

		ErrorVo errorDetails = new ErrorVo(HttpStatus.BAD_REQUEST.value(), ex.getOriginalMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> ArgumentNotValid(MethodArgumentNotValidException ex) {
		List<ObjectError> objErrorList = ex.getBindingResult().getAllErrors();
		StringBuilder errorMessage = new StringBuilder();
		for (ObjectError objError : objErrorList) {
			errorMessage.append(objError.getDefaultMessage());
			errorMessage.append(",");
		}
		ErrorVo errorDetails = new ErrorVo(HttpStatus.BAD_REQUEST.value(),
				errorMessage.deleteCharAt(errorMessage.lastIndexOf(",")).toString());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}


}
