package com.snakeeyes.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snakeeyes.api.IRequestValidator;

@Service
public class RequestValidationService {
	@Autowired
	private Set<IRequestValidator> validationRules;
	
	public void validateRequest(final String stake) {
		for(IRequestValidator validator : validationRules) {
			validator.validate(stake);
		}
	}
}
