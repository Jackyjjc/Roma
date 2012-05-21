package model;

import model.card.CardType;

public interface IField extends Iterable<IDisc> {

    public IDisc getDisc(int index);

    public ICardStorage getCardsOf(CardType type);

    public int countUnoccupiedDiscs();

    public int getNumDiscs();

    public void setLives(ICardTracker lives);

}
