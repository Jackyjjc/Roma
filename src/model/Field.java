package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.card.AbstractCard;
import model.card.CardType;

public class Field implements IField {
    
    private static final int NUM_DISCS = 7;
    private IDisc[] discs;
    
    public Field(IPlayer player) {
        initDiscs(player);
    }
    
    public List<AbstractCard> removeCardsOf(CardType type) {
        
        int length = NUM_DISCS;
        List<AbstractCard> result = new ArrayList<AbstractCard>();
        
        for(int i = 0; i < length; i++) {
            if(getCard(i).getType() == type) {
                result.add(discs[i].removeCard());
            }
        }
        
        return result;
    }
    
    public IDisc getDisc(int index) {
        return discs[index];
    }
    
    public AbstractCard getCard(int index) {
        return discs[index].getCard();
    }
    
    public int countUnoccupiedDiscs() {
        
        int count = 0;
        
        for(IDisc disc : discs) {
            if(disc.isDiscEmpty()) {
                count++;
            }
        }
        
        return count;
    }
    

    public Iterator<IDisc> iterator() {
        
        ArrayList<IDisc> list = new ArrayList<IDisc>();
        
        for(IDisc disc : discs) {
            list.add(disc);
        }
        
        return list.iterator();
    }

    public int getNumDiscs() {
        return NUM_DISCS;
    }
    
    private void initDiscs(IPlayer player) {
        
        discs = new Disc[NUM_DISCS];
        
        for(int i = 0; i < discs.length; i++) {
            discs[i] = new Disc(i);
            discs[i].setOwner(player);
        }
        
        //set up relationships
        for(int i = 0; i < discs.length - 1; i++) {
            discs[i].setNext(discs[i + 1]);
        }
        
        for(int i = discs.length - 1; i > 0; i--) {
            discs[i].setPrev(discs[i - 1]);
        }
    }
    
}
