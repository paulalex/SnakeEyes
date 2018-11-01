package com.snakeeyes.validator;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.snakeeyes.Exception.InvalidStakeException;
import com.snakeeyes.api.IRequestValidator;
import com.snakeeyes.constant.ExceptionConstant;

@Component
@Order(value=1)
public class NumericStakeValidator implements IRequestValidator {		
	@Override
	public void validate(final String stake) {
		try {
			Double.parseDouble(stake);
		} catch(final NumberFormatException ex) {
			throw new InvalidStakeException(ExceptionConstant.NON_NUMERIC_STAKE.getMessage(), ExceptionConstant.NON_NUMERIC_STAKE.getField());
		}
	}
}
