package model.card;

import framework.cards.Card;
import model.ICardResources;
import model.IGameIO;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16/05/12
 * Time: 11:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class TelephoneBox extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 2;

    public TelephoneBox(ICardResources cardResources, IGameIO gameIO) {
        super(Card.TELEPHONEBOX, CardType.BUILDING,
                COST, DEFENCE, cardResources, gameIO);
    }


}
