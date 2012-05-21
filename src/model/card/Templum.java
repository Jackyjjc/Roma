package model.card;

import framework.cards.Card;
import model.Die;
import model.ICardResources;
import model.card.behaviour.TemplumBehaviour;

public class Templum extends AbstractCard implements IForumListener, IDieChecker {

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

    public boolean isValidDie(Die die) {

        boolean isValid = false;

        if (die != null && !die.isUsed()) {
            isValid = true;
        }

        return isValid;
    }
}
