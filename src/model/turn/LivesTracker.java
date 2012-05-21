package model.turn;

import model.ICardTracker;
import model.IField;
import model.card.AbstractCard;
import model.card.Kat;
import model.card.behaviour.KatBehaviour;

import java.util.HashMap;

public class LivesTracker implements ICardTracker {

    private HashMap<Integer, Integer> katLives;

    public LivesTracker(IField field) {

        katLives = new HashMap<Integer, Integer>();

        for (int discIndex = 0; discIndex < field.getNumDiscs(); discIndex++) {

            AbstractCard card = field.getDisc(discIndex).getCard();

            if (card instanceof Kat) {
                katLives.put(discIndex, ((KatBehaviour) card.getBehaviour()).getLives());
            }
        }

    }

    public void addKat(int index, int lives) {
        katLives.put(index, lives);
    }

    public int getLives(int index) {
        int lives = 9;
        
        if (!isEmpty()) {
            lives = katLives.get(index);
        }

        return lives;
    }

    public boolean isEmpty() {
        return katLives.isEmpty();
    }
}
