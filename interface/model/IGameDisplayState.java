package model;

import java.util.Collection;
import java.util.List;

import framework.cards.Card;

public interface IGameDisplayState {

    public List<Card> getDeck ();
    
    public List<Card> getDiscard ();
    
    public int getPlayerSestertii (int playerNum);
    
    public int getPlayerVictoryPoints (int playerNum);
    
    public Collection<Card> getPlayerHand (int playerNum);
    
    public Card[] getPlayerCardsOnDiscs (int playerNum);
    
    public int[] getActionDice ();
    
    public int getPoolVictoryPoints ();
    
    public int getWhoseTurn();
    
    public int getBattleDiceValue();
    
    public List<Card> getList();
}
