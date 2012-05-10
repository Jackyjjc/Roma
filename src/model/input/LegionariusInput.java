package model.input;

import model.Input;
import framework.interfaces.MoveMaker;
import framework.interfaces.activators.LegionariusActivator;

public class LegionariusInput implements Input {

	private MoveMaker move;
	private int disc;
	
	public void run() {
		move.chooseCardToActivate(disc);
	}
	
}
