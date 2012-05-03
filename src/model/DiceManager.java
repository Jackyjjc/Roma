package model;
public class DiceManager {
	
    private Die battleDie;
	private Die[] actionDice;
	
	private Notifier notifier;
	
	public DiceManager (int numDice, Notifier notifier) {
		
	    battleDie = new Die();
		actionDice = new Die[numDice];
		
		for(int i = 0; i < actionDice.length; i++) {
		    actionDice[i] = new Die();
		}
		
		this.notifier = notifier;
	}
	
	public void rollActionDice() {
	    
	    for(Die die : actionDice) {
	        die.roll();
	    }
	}
	
	public void rollBattleDice() {
	    
	    battleDie.roll();
	    
	}

	public boolean isAllSame() {
		
		boolean theSame = true;
		
		for (int i = 0; i < actionDice.length - 1; i++) {
			if(actionDice[i].getValue() != actionDice[i+1].getValue()) {
				theSame = false;
			}
		}
		
		return theSame;
	}
	
	public Die getActionDie(int value) {
	    
	    Die die = null;
	    
	    for(int i = 0; i < actionDice.length; i++) {
	        if(actionDice[i].getValue() == value) {
	            die = actionDice[i];
	        }
	    }
	    
	    return die;
	}
	
	public Die[] getActionDice() {
	    return actionDice;
	}
	
	public Die getBattleDie() {
	    return battleDie;
	}
}

