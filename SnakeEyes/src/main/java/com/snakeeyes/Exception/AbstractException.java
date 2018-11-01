package com.snakeeyes.Exception;

public abstract class AbstractException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String field;
	
	protected AbstractException(final String message, final String field) {
		super(message);
		
		this.field = field;
	}
	
	public OperationOutcome getOperationOutcome() {
		return new OperationOutcome(field, this.getMessage());
	}
	
	public class OperationOutcome {		
		final String field;
		final String message;
		
		private OperationOutcome(final String field, final String message) {			
			this.field = field;
			this.message = message;						
		}

		public String getField() {
			return field;
		}

		public String getMessage() {
			return message;
		}				
	}
}
