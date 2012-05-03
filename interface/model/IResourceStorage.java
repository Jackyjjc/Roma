package model;

/**
 * IResourceStorage defines an abstract storage for storing
 * Money and VPs and it provides basic functionalities for
 * handling transactions.
 * 
 * @author Jacky Chen
 * @author Chris Fong
 *
 */

public interface IResourceStorage {
    
    public void setMoney(int amount);
    
    public int getMoney();
    
    public void setVP(int amount);
    
    public int getVP();
    
    public void transferMoney(IResourceStorage to, int amount);

    public void transferVP(IResourceStorage to, int amount);

}
