package model;

import java.util.ArrayList;
import java.util.List;

import model.card.AbstractCard;

public class GameRunningState {

//    private static final int MONEY_DISC = 7;
//    private static final int CARD_DISC = 0;
//    
//    private Game g;
//    private InputHandler handler;
//    
//    public GameRunningState(Game g) {
//        this.g = g;
//        this.handler = g.getInputHandler();
//    }
//    
//    public void run() {
//        
//    }
//    
//    public void onCardLay() {
//        
//        AbstractCard card = handler.getCardInput();
//        IDisc disc = handler.getDiscInput();
//        
//        IPlayer player = card.getOwner();
//        IResourceStorage bank = g.getBank();
//        
//        if(player.getMoney() >= card.getCost() && !disc.isBlocked()) {
//            player.transferMoney(bank, card.getCost());
//            card.lay(disc);
//        }
//    }
//    
//    public void onDieUse() {
//        
//        Die die = handler.getDieInput();
//        int index = handler.getDieUsedInput();
//        
//        IResourceStorage bank = g.getBank();
//        IPlayer player = g.getCurrentPlayer();
//        
//        //ICardStorage deck = g.getDeck();
//        ICardStorage deck = null;
//        
//        List<AbstractCard> selectList = new ArrayList<AbstractCard>();
//        
//        if(!die.isUsed()) {
//            
//            if(die.getValue() > CARD_DISC && die.getValue() < MONEY_DISC) {
//                
//                IDisc disc = player.getField().getDisc(die.getValue());
//                AbstractCard card = disc.getCard();
//                
//                if(!disc.isBlocked() && card != null) {
//                    if(card.activate()) {
//                        die.use();
//                    }
//                }
//            } else {
//            
//                if(index == MONEY_DISC) {
//                bank.transferMoney(player, die.getValue());
//                } else if(index == CARD_DISC) {
//                    for(int i = 0; i < die.getValue(); i++) {
//                        selectList.add(deck.popCard());
//                        //notify
//                    }
//                }
//            
//                die.use();
//            }
//        }
//    }

}
