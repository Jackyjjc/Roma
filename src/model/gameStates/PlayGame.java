package model.gameStates;

import model.Game;
import model.IDisc;
import model.ILayCardInputListener;
import model.IPlayer;
import model.IUseDieInputListener;
import model.card.AbstractCard;

public class PlayGame implements IUseDieInputListener, ILayCardInputListener {

    private Game g;
    
    private PlayGame(Game g) {
        this.g = g;
    }

    public static void initiate (Game g) {
        PlayGame playGame = new PlayGame(g);
        g.getInputHandler().addLayCardListener(playGame);
        g.getInputHandler().addDieUseListener(playGame);
    }
    
    public void update() {
        
    }

    public void layCard(int fromIndex, int toIndex) {
        IPlayer currentPlayer = g.getCurrentPlayer();
        AbstractCard card = currentPlayer.getHand().getCard(fromIndex);
        IDisc disc = currentPlayer.getField().getDisc(toIndex);
        
        g.getCurrentPlayer().getHand().removeCard(card);
        card.lay(disc);
        
        g.getNotifier().notifyListeners();
    }

    public void useDice(int dieIndex, int diceIndex) {
        
//        ICardStorage deck = g.getDeckStorage();
//        IResourceStorage bank = g.getBank();
//        IPlayer currentPlayer = g.getCurrentPlayer();
//        
//        Die[] dice = g.getDiceManager().getActionDice();
//        Die die = dice[dieIndex];
//        
//        if(diceIndex == 0) {
//            
//            for(int i = 0; i < die.getValue(); i++) {
//                currentPlayer.getHand().appendCard(deck.popCard());
//            }
//            
//        } else if(diceIndex == 7) {
//            
//            bank.transferMoney(currentPlayer, die.getValue());
//            
//        }
//        
//        g.getNotifier().notifyListeners();
    }
}
