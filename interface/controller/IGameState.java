package controller;

public interface IGameState {

    public void run();
    
    public void setNextState(IGameState state);
    
    public IGameState getNextState();
}
