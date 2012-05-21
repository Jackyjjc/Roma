package model;

/**
 * Class that manage the dice and battle dice
 *
 * @author Jacky CHEN
 * @author Chris Fong
 */

public class DiceManager {

    private Die battleDie;
    private Die[] actionDice;

    public DiceManager(int numDice) {

        battleDie = new Die();
        actionDice = new Die[numDice];

        for (int i = 0; i < actionDice.length; i++) {
            actionDice[i] = new Die();
        }
    }

    public void rollActionDice() {
        for (Die die : actionDice) {
            die.roll();
        }
    }

    public void rollBattleDice() {
        battleDie.roll();
    }

    public boolean isAllSame() {

        boolean theSame = true;

        for (int i = 0; i < actionDice.length - 1; i++) {
            if (actionDice[i].getValue() != actionDice[i + 1].getValue()) {
                theSame = false;
            }
        }

        return theSame;
    }

    public void setActionDice(int[] dice) {
        for (int i = 0; i < dice.length; i++) {
            actionDice[i].setValue(dice[i]);
        }
    }

    public Die getActionDie(int value) {

        Die die = null;

        for (int i = 0; i < actionDice.length; i++) {
            if (actionDice[i].getValue() == value
                    && !actionDice[i].isUsed()) {
                die = actionDice[i];
            }
        }

        return die;
    }

    public int[] getActionDiceValues() {

        int numUnused = 0;

        for (Die die : actionDice) {
            if (!die.isUsed()) {
                numUnused++;
            }
        }

        int[] values = new int[numUnused];

        int i = 0;
        for (Die die : actionDice) {
            if (!die.isUsed()) {
                values[i] = die.getValue();
                i++;
            }
        }

        return values;
    }

    public Die getBattleDie() {
        return battleDie;
    }
}

