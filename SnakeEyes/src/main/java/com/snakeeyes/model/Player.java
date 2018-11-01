package com.snakeeyes.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.snakeeyes.api.IPlayer;

@Component
public class Player implements IPlayer {
	@Value("${player-balance}")
	private double funds = 1000.00;
	
	@Override
	public double getFunds() {
		return funds;
	}
		
	@Override
	public void deductFunds(final double funds) {
		this.funds -= funds;
	}

	@Override
	public void addFunds(final double funds) {
		this.funds += funds;		
	}		
}
