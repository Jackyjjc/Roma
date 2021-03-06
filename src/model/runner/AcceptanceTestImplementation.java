package model.runner;

import framework.interfaces.AcceptanceInterface;
import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;
import model.Game;

public class AcceptanceTestImplementation implements AcceptanceInterface {

    private static final int NUM_PLAYER = 2;

    public AcceptanceTestImplementation() {

    }

    public MoveMaker getMover(GameState state) {

        return new GameController((Game) state);
    }

    public GameState getInitialState() {

        return new Game(NUM_PLAYER);
    }

}
