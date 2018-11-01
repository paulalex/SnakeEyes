package com.snakeeyes.Exception;

public class InvalidFundsException extends AbstractException {	
	private static final long serialVersionUID = 1L;
	
	public InvalidFundsException(final String message, final String field) {
		super(message, field);
	}
}
