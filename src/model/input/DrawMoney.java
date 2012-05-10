package model.input;

import model.Input;
import framework.interfaces.MoveMaker;

public class DrawMoney implements Input {
	
	private MoveMaker move;
	private int diceToUse;
	
	public DrawMoney (MoveMaker move, int diceToUse) {
		
		this.move = move;
		this.diceToUse = diceToUse;
		
	}
	
	public void run () {
		
		move.activateMoneyDisc(diceToUse);
		
	}

}
