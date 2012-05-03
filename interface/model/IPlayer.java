package model;

/**
 * 
 * Player is a ResourceStorage a well because it store the resources
 * But it has more functionalities
 * 
 * @author Jacky Chen
 * @author Chris Fong
 *
 */

public interface IPlayer extends IResourceStorage {
    
    public int getId();
    
    public void setOpponent(IPlayer player);
    
    public IPlayer getOpponent();
    
    public IField getField();
    
    public ICardStorage getHand();
}
