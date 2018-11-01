package com.snakeeyes.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.snakeeyes.api.IPaymentCalculator;
import com.snakeeyes.model.GameResult;

@Service
public class PaymentService {			
	@Autowired
	private Set<IPaymentCalculator> calculationRules;
	
	@Value("${no-payout-name}")
	private String noPayoutName;
	
	public GameResult calculateWinnings(final double stake, final String diceRollResult) {		
		final String[] diceValues = diceRollResult.replace("\t", "").split("");
		final int diceOne = Integer.valueOf(diceValues[0]);
		final int diceTwo = Integer.valueOf(diceValues[1]);
		
		return calculatePayment(diceOne, diceTwo, stake);					
	}
	
	private GameResult calculatePayment(final int diceOne, final int diceTwo, final double stake) {
		double winnings = 0.0;
		String payoutName = "";
		
		for(IPaymentCalculator calculator : calculationRules) {						
			winnings = calculator.calculate(diceOne, diceTwo, stake);
						
			if(winnings > 0.0) {
				payoutName = calculator.getPayoutName();
				break;
			}
		}
						
		return buildGameResult(diceOne, diceTwo, stake, winnings, (payoutName.equals("") ? noPayoutName : payoutName));
	}
		
	private GameResult buildGameResult(final int diceOneValue, 
			final int diceTwoValue, 
			final double stake,
			final double winnings,
			final String payoutName) {
		
		return GameResult.builder()
				.withDiceOne(diceOneValue)
				.withDiceTwo(diceTwoValue)
				.withStake(stake)
				.withWinnings(winnings)
				.withPayoutName(payoutName)	
				.build();
						
	}	
}
