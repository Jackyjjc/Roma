package model.card.behaviour;

import model.IField;
import model.IPlayer;
import model.card.AbstractCard;
import model.card.Action;
import model.card.Forum;

public class MercatusBehaviour extends Behaviour {

    public MercatusBehaviour(AbstractCard host) {
        super(host);
    }

    public void complete() {

        IPlayer opponent = getHost().getOwner().getOpponent();
        IField discs = opponent.getField();
        AbstractCard c = null;

        int numOfForum = 0;

        for (int i = 0; i < discs.getNumDiscs(); i++) {
            c = discs.getDisc(i).getCard();
            if (c != null && c instanceof Forum) {
                numOfForum++;
            }
        }

        Action.attainVP(opponent, getHost().getOwner(), numOfForum);

    }
}
