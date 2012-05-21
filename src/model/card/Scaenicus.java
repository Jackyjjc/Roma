package model.card;

import model.ICardResources;
import model.card.behaviour.Behaviour;
import model.card.behaviour.ScaenicusBehaviour;
import framework.cards.Card;

public class Scaenicus extends AbstractCard {

    private static final int COST = 8;
    private static final int DEFENCE = 3;

    private Scaenicus(ICardResources cardResources) {

        super(Card.SCAENICUS, 
              CardType.CHARACTER,
              COST, 
              DEFENCE, 
              cardResources);

    }

    @Override
    public Behaviour getBehaviour() {
        ScaenicusBehaviour behaviour = (ScaenicusBehaviour) super.getBehaviour();
        return behaviour.getMimicBehaviour();
    }
    
    static AbstractCard create(ICardResources cardResources, CardFactory factory) {
        
        AbstractCard card = new Scaenicus(cardResources);
        card.setBehaviour(new ScaenicusBehaviour(card, factory));
    
        return card;
    }
}
