package model.card;

import model.IDisc;
import model.IResourceStorage;

public class Action {
    
    public static void attack(AbstractCard c, int value) {
        if(c.getDefence() <= value) {
            c.disCard();
        }
    }
    
    public static void layForFree(AbstractCard c, IDisc disc) {
        
        c.setCost(0);
        disc.layCard(c);
        c.setCost(c.getDefaultCost());
    }
    
    public static void attainVP (IResourceStorage from, IResourceStorage to, int amount) {
        
        if (from.getVP() >= amount) {
            from.transferVP(to, amount);
        }
    
    }
    
}
