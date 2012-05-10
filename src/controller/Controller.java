package controller;

import gui.GraphicalView;

import javax.swing.JOptionPane;

import model.Game;
import model.ICardStorage;
import model.IPlayer;
import model.LayCard;
import model.Notifier;
import model.PlayGame;
import model.SwapCard;
import model.card.AbstractCard;

public class Controller {

    private static final int NUM_PLAYERS = 2;
    private static final int SWAP_NUM_CARDS = 2;
    private static final int NUM_DICE = 3;
    private static final int START_NUM_CARDS = 4;

    public static void main(String[] args) {
        
        Game g = new Game(NUM_PLAYERS);        
        Notifier notifier = g.getNotifier();
        GraphicalView view = new GraphicalView(g);
        notifier.addListener(view);
        view.pack();
        view.setVisible(true);
        
        notifier.notifyListeners();
        
        dealCard(g);
        
        JOptionPane.showMessageDialog(null, "Please select " +SWAP_NUM_CARDS +" cards to swap with your opponent! :) " , "Roma", 1);
        
        SwapCard.initiate(g);
        LayCard.initiate(g);
        
    }
    
    public static String getName(int playerId) {
        
        String str = null;
        do {
          str = JOptionPane.showInputDialog(null, "Enter your name: ", "Player " + playerId, 1);
        } while(str == null || str.length() == 0);
        
        //if player cancel then leave the game
        
        System.out.println(str);
        
        return str;
    }
    
    public static String getAge(int playerId) {
        
        String str = null;
        do {
          str = JOptionPane.showInputDialog(null, "Enter your age: ", "Player " + playerId, 1);
        } while(str == null || str.length() == 0);
        
        System.out.println(str);
        
        return str;
    }
    
    public static void dealCard(Game g) {
        
        ICardStorage deck = g.getDeckStorage();
        
        IPlayer player = g.getCurrentPlayer();
        
        for(int i = 0; i < NUM_PLAYERS; i++) {
            
            for(int j = 0; j < START_NUM_CARDS; j++) {
                AbstractCard drawnCard = deck.popCard();
                player.getHand().pushCard(drawnCard);
            }
            
            player = player.getOpponent();
        }
        
        g.getNotifier().notifyListeners();
    }
    
}
