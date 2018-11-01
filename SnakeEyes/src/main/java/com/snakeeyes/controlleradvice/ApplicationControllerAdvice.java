package com.snakeeyes.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.snakeeyes.Exception.AbstractException.OperationOutcome;
import com.snakeeyes.Exception.InvalidFundsException;
import com.snakeeyes.Exception.InvalidStakeException;

@ControllerAdvice
public class ApplicationControllerAdvice {
	@ExceptionHandler(InvalidStakeException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public OperationOutcome processValidationError(final InvalidStakeException exception) {	    
	    return exception.getOperationOutcome();
	}
	
	@ExceptionHandler(InvalidFundsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public OperationOutcome processValidationError(final InvalidFundsException exception) {		
		 return exception.getOperationOutcome();
	}
}
