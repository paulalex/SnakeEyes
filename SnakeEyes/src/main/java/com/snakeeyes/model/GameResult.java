package com.snakeeyes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameResult {
	@JsonProperty("dice1")
	private int diceOne;
	
	@JsonProperty("dice2")
	private int diceTwo;
	
	private double stake;
	private double winnings;
	
	@JsonProperty("payout_name")
	private String payoutName;
	
	private GameResult(	final int diceOne, 
						final int diceTwo, 
						final double stake, 
						final double winnings, 
						final String payoutName) {
		
		this.diceOne = diceOne;
		this.diceTwo = diceTwo;
		this.stake = stake;
		this.winnings = winnings;
		this.payoutName = payoutName;
	}
	
	public int getDiceOne() {
		return diceOne;
	}
	
	public int getDiceTwo() {
		return diceTwo;
	}
	
	public double getStake() {
		return stake;
	}
	
	public double getWinnings() {
		return winnings;
	}
	
	public String getPayoutName() {
		return payoutName;
	}
	
	public static GameResultBuilder builder() {
		return new GameResultBuilder();
	}
	
	public static class GameResultBuilder {		
		private int diceOne;			
		private int diceTwo;		
		private double stake;
		private double winnings;			
		private String payoutName;		
		
		public GameResultBuilder withDiceOne(final int diceOne) {
			this.diceOne = diceOne;
			
			return this;
		}
		
		public GameResultBuilder withDiceTwo(final int diceTwo) {
			this.diceTwo = diceTwo;
			
			return this;
		}
		
		public GameResultBuilder withStake(final double stake) {
			this.stake = stake;
			
			return this;
		}
		
		public GameResultBuilder withWinnings(final double winnings) {
			this.winnings = winnings;
			
			return this;
		}
		
		public GameResultBuilder withPayoutName(final String payoutName) {
			this.payoutName = payoutName;
			
			return this;
		}
		
		public GameResult build() {
			return new GameResult(	this.diceOne,
									this.diceTwo,
									this.stake,
									this.winnings,
									this.payoutName
								);
		}
	}
}
