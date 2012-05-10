package model;

import java.util.List;

import model.card.AbstractCard;
import model.card.CardType;

public interface IField extends Iterable<IDisc> {

    public int countUnoccupiedDiscs();
    
    public AbstractCard getCard(int index);
    
    public IDisc getDisc(int index);
    
    public List<AbstractCard> removeCardsOf(CardType type);
    
    public int getNumDiscs();
}
