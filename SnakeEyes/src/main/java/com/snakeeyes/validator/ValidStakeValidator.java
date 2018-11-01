package com.snakeeyes.validator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.snakeeyes.Exception.InvalidStakeException;
import com.snakeeyes.api.IRequestValidator;
import com.snakeeyes.constant.ExceptionConstant;

@Component
@Order(value = 2)
public class ValidStakeValidator implements IRequestValidator {
	private static final Set<Double> ALLOWED_STAKES = new HashSet<>(Arrays.asList(1.00, 2.00, 10.00));

	@Override
	public void validate(final String stake) {
		if(!ALLOWED_STAKES.contains(Double.parseDouble(stake))) {
			throw new InvalidStakeException(ExceptionConstant.INVALID_STAKE.getMessage(), ExceptionConstant.INVALID_STAKE.getField());
		}
	}
}
