package model.action;

import framework.interfaces.GameState;

public abstract class Action {

    private GameState g;

    public Action(GameState g) {
        this.g = g;
    }

    public GameState getGameState() {
        return g;
    }

    public abstract void run();

    public abstract boolean isValid();
}
