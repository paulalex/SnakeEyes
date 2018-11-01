package com.snakeeyes.Exception;

public class InvalidStakeException extends AbstractException {
	private static final long serialVersionUID = 1L;
	
	public InvalidStakeException(final String message, final String field) {
		super(message, field);
	}
}
