package model;

import model.card.AbstractCard;
import model.card.CardType;

public interface IField extends Iterable<IDisc> {

    public int countUnoccupiedDiscs();
    
    public AbstractCard getCard(int index);
    
    public IDisc getDisc(int index);
    
    public ICardStorage removeCardsOf(CardType type);
    
    public int getNumDiscs();
}
