package model;

import framework.cards.Card;
import model.card.CardFactory;
import model.cardcollection.CardCollectionFactory;

/**
 * Player class which extends resources
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

class Player extends ResourceStorage implements IPlayer {

    private static final int START_VP = 10;
    private static final int START_MONEY = 0;

    private int id;
    private ICardStorage hand;
    private IPlayer opponent;
    private IField field;

    public static IPlayer createPlayer(int id, CardFactory factory,
                                       ITurnMover turnMover, IResourceStorage bank) {

        IPlayer player = new Player(id, factory, turnMover, bank);
        bank.transferMoney(player, START_MONEY);
        bank.transferVP(player, START_VP);

        return player;
    }

    public int getId() {
        return id;
    }

    public ICardStorage getHand() {
        return hand;
    }

    public void setOpponent(IPlayer player) {
        this.opponent = player;
    }

    public IPlayer getOpponent() {
        return opponent;
    }

    public IField getField() {
        return field;
    }

    //use construction method to solve the problem of initiallizing the player
    private Player(int id, CardFactory factory,
                   ITurnMover turnMover, IResourceStorage bank) {

        super(0, 0, turnMover);
        this.id = id;
        this.hand = CardCollectionFactory.create(factory);
        this.hand.setOwner(this);
        hand.appendCard(factory.create(Card.SCAENICUS));
        hand.appendCard(factory.create(Card.SCAENICUS));
        this.field = Field.createField(turnMover, this, bank);
    }
}
