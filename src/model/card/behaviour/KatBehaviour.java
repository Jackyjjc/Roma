package model.card.behaviour;

import model.ICardResources;
import model.card.AbstractCard;

public class KatBehaviour extends Behaviour {

    private static final int DEFAULT_LIVES = 9;

    public KatBehaviour(AbstractCard host, ICardResources cardResources) {
        super(host, cardResources);
        reset();
    }

    public void complete() {
        System.out.println("http://www.youtube.com/watch?v=QH2-TGUlwu4");
    }

    @Override
    void reset() {
        setLives(DEFAULT_LIVES);
    }

}
