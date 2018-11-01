package com.snakeeyes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snakeeyes.Exception.InvalidFundsException;
import com.snakeeyes.api.IPlayer;
import com.snakeeyes.constant.ExceptionConstant;
import com.snakeeyes.model.GameResult;

@Service
public class GameService {
	private static final Logger LOG = LoggerFactory.getLogger(GameService.class);
	
	@Autowired
	private IPlayer player;
	
	@Autowired
	private DiceRollService numberService;
	
	@Autowired 
	private PaymentService paymentCalculationService;
	
	public GameResult play(final String stake) {	
		final Double stakeNumeric = Double.parseDouble(stake);
		
		validateAccountBalance(stakeNumeric);
		
		LOG.debug("Request validated");
		
		player.deductFunds(stakeNumeric);
		
		String diceRollResult = numberService.rollDice(2, 1, 6, 2, 10);
		
		final GameResult result = paymentCalculationService.calculateWinnings(stakeNumeric, diceRollResult);
		
		player.addFunds(result.getWinnings());
		
		LOG.debug("Balance: " + player.getFunds());
		
		return result;
	}
	
	private void validateAccountBalance(final double stake) {
		if(player.getFunds() < stake) {
			throw new InvalidFundsException(ExceptionConstant.FUNDS_TOO_LOW.getMessage(), ExceptionConstant.FUNDS_TOO_LOW.getField());
		}
	}
}
