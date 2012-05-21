package model;

/**
 * class the implement the functionalities of a bribe disc
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class BribeDisc extends Disc implements IDisc {

    private IResourceStorage bank;
    private int bribe;

    public BribeDisc(int index, IPlayer owner, ITurnMover turnMover, IResourceStorage bank) {
        super(index, owner, turnMover);
        this.bank = bank;
        this.bribe = 0;
    }

    @Override
    public void activateCard() {

        if (getOwner().getMoney() >= bribe && !isBlocked()) {
            getOwner().transferMoney(bank, bribe);
            super.activateCard();
            bribe = 0;
        }

    }

    public void giveBribe(int diceToUse) {
        this.bribe = diceToUse;
    }

}
