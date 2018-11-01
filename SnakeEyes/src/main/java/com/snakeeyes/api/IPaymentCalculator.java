package com.snakeeyes.api;

public interface IPaymentCalculator {	
	
	double calculate(final int diceOne, final int diceTwo, final double stake);
	
	String getPayoutName();
}
