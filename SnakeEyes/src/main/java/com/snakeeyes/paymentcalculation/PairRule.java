package com.snakeeyes.paymentcalculation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.snakeeyes.api.IPaymentCalculator;;

@Component
@Order(value=2)
public class PairRule implements IPaymentCalculator {	
	@Value("${pair-multiplier}")
	private int pairMultiplier;
	
	@Value("${pair-payout-name}")
	private String payoutName;
	
	@Override
	public double calculate(final int diceOne, final int diceTwo, final double stake) {
		double winnings = 0.0;
		
		if(diceOne == diceTwo) {
			winnings = stake * pairMultiplier;
		}
		
		return winnings;
	}

	@Override
	public String getPayoutName() {		
		return payoutName;
	}		
}
