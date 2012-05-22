package model.card;

import framework.cards.Card;
import model.ICardResources;
import model.card.behaviour.Behaviour;
import model.card.behaviour.ScaenicusBehaviour;

public class Scaenicus extends AbstractCard {

    private static final int COST = 8;
    private static final int DEFENCE = 3;

    private Scaenicus() {

        super(Card.SCAENICUS,
                CardType.CHARACTER,
                COST,
                DEFENCE);

    }
    
    @Override
    public Behaviour getBehaviour() {
        ScaenicusBehaviour behaviour = (ScaenicusBehaviour) super.getBehaviour();
        return behaviour.getMimicBehaviour();
    }

    static AbstractCard create(ICardResources cardResources, CardFactory factory) {

        AbstractCard card = new Scaenicus();
        card.setBehaviour(new ScaenicusBehaviour(card, cardResources, factory));

        return card;
    }
}
