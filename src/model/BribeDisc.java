package model;

public class BribeDisc extends Disc implements IDisc{

	private IResourceStorage bank;
	private int bribe;
	
	public BribeDisc(ITurnMover turnMover, int index, IResourceStorage bank) {
		super(turnMover, index);
		this.bank = bank;
	}
	
	@Override
	public void activateCard() {
		
		if(getOwner().getMoney() >= bribe && !isBlocked()) {
			getOwner().transferMoney(bank, bribe);
			super.activateCard();
		}
		
	}
	
	public void giveBribe (int diceToUse) {
		this.bribe = diceToUse;
	}
	
}
