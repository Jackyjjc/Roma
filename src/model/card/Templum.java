package model.card;

import model.ICardResources;
import model.card.behaviour.TemplumBehaviour;
import framework.cards.Card;

public class Templum extends AbstractCard implements IForumListener {

    private static final int COST = 2;
    private static final int DEFENCE = 2;

    private Templum() {

        super(Card.TEMPLUM,
                CardType.BUILDING,
                COST,
                DEFENCE);

    }

    static AbstractCard create(ICardResources cardResources) {

        AbstractCard card = new Templum();
        card.setBehaviour(new TemplumBehaviour(card, cardResources));

        return card;
    }

    public void alert() {
        ((IForumListener) getBehaviour()).alert();
    }
}
