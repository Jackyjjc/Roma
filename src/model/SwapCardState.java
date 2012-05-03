package model;


public class SwapCardState {

 /*   private static final int SWAP_NUM_CARDS = 2;
    
    private Game g;
    private InputHandler handler;
    
    public SwapCardState(Game g) {
        this.g = g;
        handler = g.getInputHandler();
    }
    
    public void run() {
        
        IPlayer player = g.getCurrentPlayer();
        int numPlayers = g.getNumPlayers();

        //ICardStorage tempStorage = CardCollectionFactory.create(false, factory);
        ICardStorage tempStorage = null;

        ICardStorage hand = null;
        AbstractCard temp = null;
        
        //swap cards between players
        for(int i = 0; i < numPlayers; i++) {
            
            //each player need to swap SWAP_NUM_CARD
            for(int j = 0; j < SWAP_NUM_CARDS; j++) {
                
                hand = g.getCurrentPlayer().getHand();
                
                //choose which card to give up
                temp = handler.getCardInput();
                hand.removeCard(temp);
                
                tempStorage.appendCard(temp);
            }
            
            player = player.getOpponent();
        }
        
        //start from the second player
        player = player.getOpponent();
        
        //take the card from the opponent
        for(int i = 0; i < numPlayers; i++) {
            for(int j = 0; j < SWAP_NUM_CARDS; j++) {
                
                player.getHand().pushCard(tempStorage.popCard());
            }
            
            player = player.getOpponent();
        }
        
        g.setState(new LayCardState(g));
    }*/

}
