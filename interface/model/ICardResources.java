package model;

public interface ICardResources {

    public IResourceStorage getBank();
    
    public ICardStorage getDeckStorage();
    
    public ICardStorage getDiscardStorage();
    
    public DiceManager getDiceManager();

    public void addTurnListener(ITurnListener listener);
    
    public void removeTurnListener(ITurnListener listener);
    
}
