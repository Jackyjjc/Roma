package model.input;

import model.Input;
import framework.cards.Card;
import framework.interfaces.MoveMaker;

public class LayCardFromHand implements Input {

	private MoveMaker move;
	private Card toPlace;
	private int discToPlaceOn;
	

	public LayCardFromHand(MoveMaker move, Card toPlace, int discToPlaceOn) {
		
		this.move = move;
		this.toPlace = toPlace;
		this.discToPlaceOn = discToPlaceOn;
		
	}
	
	public void run() {
		
		move.placeCard(toPlace, discToPlaceOn);
		
	}

}
