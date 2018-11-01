package com.snakeeyes.paymentcalculation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.snakeeyes.api.IPaymentCalculator;;

@Component
@Order(value=1)
public class SnakeEyesRule implements IPaymentCalculator {
	
	@Value("${snake-eye-multiplier}")
	private int snakeEyeMultiplier;
	
	@Value("${snake-eye-payout-name}")
	private String payoutName;
	
	@Override
	public double calculate(final int diceOne, final int diceTwo, final double stake) {
		double winnings = 0.0;
		
		if(diceOne == 1 && diceTwo == 1) {
			winnings = stake * snakeEyeMultiplier;
		}
		
		return winnings;
	}

	@Override
	public String getPayoutName() {		
		return payoutName;
	}		
}
