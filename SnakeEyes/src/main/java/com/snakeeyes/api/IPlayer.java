package com.snakeeyes.api;

public interface IPlayer {
	double getFunds();
	
	void addFunds(final double funds);
	
	void deductFunds(final double funds);
}
