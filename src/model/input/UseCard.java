package model.input;

import model.Input;
import framework.cards.Card;
import framework.interfaces.MoveMaker;

public class UseCard implements Input {

	private MoveMaker move;
	private Card[][] playerDiscs;
	private int disc;
	private int playerId;
	
    public UseCard (MoveMaker move, Card[][] playerDiscs, int playerId, int disc) {
    	
    	this.move = move;
    	this.playerDiscs = playerDiscs;
    	this.disc = disc;
    	this.playerId = playerId;
    	
    }
	
	public void run() {
		
		Card c = playerDiscs[playerId][disc];
		
		
	}

	
	
}
