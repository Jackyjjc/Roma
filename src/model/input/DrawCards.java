package model.input;

import model.Input;
import framework.cards.Card;
import framework.interfaces.MoveMaker;

public class DrawCards implements Input {
	
	private MoveMaker move;
	private Card chosen;
	private int diceToUse;
	
	public DrawCards(MoveMaker move, Card chosen, int diceToUse) {
		
		this.move = move;
		this.chosen = chosen;
		this.diceToUse = diceToUse;
		
	}
	
	public void run() {
		move.activateCardsDisc(diceToUse, chosen);
	}
	
}
