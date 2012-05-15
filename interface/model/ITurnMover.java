package model;

public interface ITurnMover {

    public void addTurnListener(ITurnListener listener);
    
    public void removeTurnListener(ITurnListener listener);
    
    public Turn getTurn (int numTurnsAgo);
    
    public void replay(int numTurnsAgo);
    
    public void gameOver();
    
}
