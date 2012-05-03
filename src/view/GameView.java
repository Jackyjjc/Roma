package view;
import model.ICardStorage;
import model.card.AbstractCard;


/*
 * Roma
 * Version 2.0 */

public class GameView {
	
	private static final int USE_DICE = 1;
	private static final int LAY_CARD = 2;
	private static final int PASS = 3;
	
	public GameView () {

	}
	
	public void printEndTurn(int player) {
	    System.out.println("Player " + player + "turn ends");
	}

	public void printMenu() {
        System.out.println("Would you like to:\n" + 
                "- usedice\n" + 
                "- laycard\n" +
                "- pass\n" + 
                "- help");
	}
	
	public void printRerollMessage() {
        System.out.println("All dice are the same. Do you want a reroll?");
        System.out.println("1. Yes");
        System.out.println("2. No");
	}
	
    public void printNumUnoccupiedDisc(int num) {
        System.out.println("You have " + num + " unoccupied disc");
    }

    public void printTurn(int numTurn, String name) {
        System.out.println("Turn #" + numTurn + " : It's" + name +"'s turn.");
    }

    public void printSwapCardMessage(String name) {
        System.out.println(name + " Choose a card to give to your opponent");
    }

    public void printWelcome() {
		
		System.out.println("//=================================================\\");
		System.out.println("||                                                 ||");
		System.out.println("||                                                 ||");
		System.out.println("||                     Welcome                     ||");
		System.out.println("||                       to                        ||");
		System.out.println("||                      ROMA                       ||");
		System.out.println("||                                                 ||");
		System.out.println("||                                                 ||");
		System.out.println("\\=================================================//");
		System.out.println("                        |  |");
		System.out.println("                        |  |");
		System.out.println("                        |  |");
		System.out.println("                        |  |");
		System.out.println("                        |__|");
	}


	
	public void printCards(ICardStorage cards) {
	    
	    int length = cards.size();
	    
	    for(int i = 0; i < length; i++) {
	        System.out.println(i + ". " + cards.getCard(i) + "|");
	    }
	}
	
	public void printField(AbstractCard[][] field, int numPlayer) {
	    
	    for(int i = 0; i < numPlayer; i++) {
	        
	        System.out.print("Player " + i);
	        System.out.print("| ");
	        
	        for(int j = 0; j < 6; j++) {
	        
	            if(field[i][j] == null) {
	                System.out.print("        " + " | ");
	            } else {
	                System.out.print(field[i][j] + " | ");
	            }
	        }
	        
	        System.out.println();
	    }
	    
	    System.out.println();
	}
	
/*	public void printState(IPlayer p, IPlayer[] players, AbstractCard[][] field) {
	    
	    //print your own information
	    System.out.println("You have " + 
	                       p.getMoney() + " sesterii and " + 
	                       p.getVP() + " victory points");
	    
	    //print your opponent's information
	    for (int i = 0; i < players.length; i++) {
	        if (i != p.getId()) {
	            System.out.println ("Player " + i + " has " + 
	                                players[i].getMoney() + " sesterii and " + 
	                                players[i].getVP() + " victory points");
	        }
	    }
	    
	    printField(field, players.length);
	    
	    printCards(p.getHand(null));
	    
	}*/

    public void printLayHand(int i) {
        System.out.println("player " + i + " please choose are card to lay");
    }
}
