package model.action;

import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;

public abstract class MoveMakingAction extends Action {

    private MoveMaker moveMaker;
    
    public MoveMakingAction(GameState g, MoveMaker moveMaker) {
        super(g);
        this.moveMaker = moveMaker;
    }
    
    public MoveMaker getMoveMaker() {
        return moveMaker;
    }
    
}
