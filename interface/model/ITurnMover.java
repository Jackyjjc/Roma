package model;

public interface ITurnMover {

    public void addTurnListener(ITurnListener listener);
    
    public void removeTurnListener(ITurnListener listener);
}