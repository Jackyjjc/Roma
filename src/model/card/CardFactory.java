package model.card;

import model.ICardResources;
import framework.cards.Card;

/**
 * 
 * Reviewed at 20/05/2012
 * 
 * @author Chris Fong
 * @author Junjie CHEN
 *
 */

public class CardFactory {

    private ICardResources cardResources;

    public CardFactory(ICardResources cardResources) {

        this.cardResources = cardResources;
    }

    public AbstractCard create(Card name) {

        AbstractCard card = null;
        
        switch(name) {
            case AESCULAPINUM:
                card = Aesculapinum.create(cardResources);
                break;
            case ARCHITECTUS:
                card = Architectus.create(cardResources);
                break;
            case BASILICA:
                card = Basilica.create(cardResources);
                break;
            case CENTURIO:
                card = Centurio.create(cardResources);
                break;
            case CONSILIARIUS:
                card = Consiliarius.create(cardResources);
                break;
            case CONSUL:
                card = Consul.create(cardResources);
                break;
            case ESSEDUM:
                card = Essedum.create(cardResources);
                break;
            case FORUM:
                card = Forum.create(cardResources);
                break;
            case GLADIATOR:
                card = Gladiator.create(cardResources);
                break;
            case GRIMREAPER:
                card = GrimReaper.create(cardResources);
                break;
            case HARUSPEX:
                card = Haruspex.create(cardResources);
                break;
            case KAT:
                card = Kat.create(cardResources);
                break;
            case LEGAT:
                card = Legat.create(cardResources);
                break;
            case LEGIONARIUS:
                card = Legionarius.create(cardResources);
                break;
            case MACHINA:
                card = Machina.create(cardResources);
                break;
            case MERCATOR:
                card = Mercator.create(cardResources);
                break;
            case MERCATUS:
                card = Mercatus.create(cardResources);
                break;
            case NERO:
                card = Nero.create(cardResources);
                break;
            case NOT_A_CARD:
                break;
            case ONAGER:
                card = Onager.create(cardResources);
                break;
            case PRAETORIANUS:
                card = Praetorianus.create(cardResources);
                break;
            case SCAENICUS:
                card = Scaenicus.create(cardResources, this);
                break;
            case SENATOR:
                card = Senator.create(cardResources);
                break;
            case SICARIUS:
                card = Sicarius.create(cardResources);
                break;
            case TELEPHONEBOX:
                card = TelephoneBox.create(cardResources);
                break;
            case TEMPLUM:
                card = Templum.create(cardResources);
                break;
            case TRIBUNUSPLEBIS:
                card = TribunusPlebis.create(cardResources);
                break;
            case TURRIS:
                card = Turris.create(cardResources);
                break;
            case VELITES:
                card = Velites.create(cardResources);
                break;

        }

        return card;
    }
}
