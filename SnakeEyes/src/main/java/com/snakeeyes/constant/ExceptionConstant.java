package com.snakeeyes.constant;

public enum ExceptionConstant {
	NON_NUMERIC_STAKE("Invalid Numeric Value", "stake"),
	INVALID_STAKE("Invalid Value", "stake"),
	FUNDS_TOO_LOW("Funds Too Low", "funds");
	
	private ExceptionConstant(final String message, final String field) {
		this.message = message;
		this.field = field;
	}
	
	private String message;
	private String field;
	
	public String getMessage() {
		return this.message;
	}
	
	public String getField() {
		return this.field;
	}
}
