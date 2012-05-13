package model;

import model.card.CardFactory;
import model.cardcollection.CardCollectionFactory;

class Player implements IPlayer {

    private static final int START_MONEY = 10;
    private static final int START_VP = 10;
    
    private int id;
    private IResourceStorage rs;
    private ICardStorage hand;
    private IPlayer opponent;
    private IField field;
    
    public static IPlayer createPlayer(ITurnMover turnMover, int id, IResourceStorage bank, CardFactory factory) {
        
        IPlayer player = new Player(turnMover, id, factory);
        
        bank.transferMoney(player, START_MONEY);
        bank.transferVP(player, START_VP);
        
        return player;
    }
    
    //use construction method to solve the problem of initiallizing the player
    private Player(ITurnMover turnMover, int id, CardFactory factory) {
        this.id = id;
        this.rs = new ResourceStorage(0, 0);
        this.hand = CardCollectionFactory.create(false, factory);
        this.hand.setOwner(this);
        this.field = new Field(this,turnMover);
    }

    public int getId() {
        return id;
    }
    
    public void setMoney(int amount) {
        rs.setMoney(amount);
    }

    public int getMoney() {
        return rs.getMoney();
    }

    public void setVP(int amount) {
        rs.setVP(amount);
    }

    public int getVP() {
        return rs.getVP();
    }

    public void transferMoney(IResourceStorage to, int amount) {
        rs.transferMoney(to, amount);
    }
    
    public void transferVP(IResourceStorage to, int amount) {
        rs.transferVP(to,amount);
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

    
}
