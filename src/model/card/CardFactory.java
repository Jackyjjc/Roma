package model.card;

import model.ICardResources;
import model.Notifier;
import framework.cards.Card;

public class CardFactory {
    
    private ICardResources cardResources;
    private Notifier notifier;
    
    public CardFactory(ICardResources cardResources) {
        
        this.cardResources = cardResources;
        
    }
    
    public AbstractCard create(Card name) {
        
        AbstractCard card = null;
        
        switch(name) {
            case AESCULAPINUM:
                card = new Aesculapinum(cardResources, notifier);
                break;
            case ARCHITECTUS: 
                card = new Architectus(cardResources, notifier);
                break;
            case BASILICA:
                card = new Basilica(cardResources, notifier);
                break;
            case CENTURIO:
                card = new Centurio(cardResources, notifier);
                break;
            case CONSILIARUS:
                card = new Consiliarius(cardResources, notifier);
                break;
            case CONSUL:
                card = new Consul(cardResources, notifier);
                break;
            case ESSEDUM:
                card = new Essedum(cardResources, notifier);
                break;
            case FORUM:
                card = new Forum(cardResources, notifier);
                break;
            case GLADIATOR:
                card = new Gladiator(cardResources, notifier);
                break;
            case HARUSPEX:
                card = new Haruspex(cardResources, notifier);
                break;
            case LEGAT:
                card = new Legat(cardResources, notifier);
                break;
            case LEGIONARIUS:
                card = new Legionarius(cardResources, notifier);
                break;
            case MACHINA:
                card = new Machina(cardResources, notifier);
                break;
            case MERCATOR:
                card = new Mercator(cardResources, notifier);
                break;
            case MERCATUS:
                card = new Mercatus(cardResources, notifier);
                break;
            case NERO:
                card = new Nero(cardResources, notifier);
                break;
            case NOT_A_CARD:
                break;
            case ONAGER:
                card = new Onager(cardResources, notifier);
                break;
            case PRAETORIANUS:
                card = new Praetorianus(cardResources, notifier);
                break;
            case SCAENICUS:
                card = new Scaenicus(cardResources, this, notifier);
                break;
            case SENATOR:
                card = new Senator(cardResources, notifier);
                break;
            case SICARIUS:
                card = new Sicarius(cardResources, notifier);
                break;
            case TEMPLUM:
                card = new Templum(cardResources, notifier);
                break;
            case TRIBUNUSPLEBIS:
                card = new TribunusPlebis(cardResources, notifier);
                break;
            case TURRIS:
                card = new Turris(cardResources, notifier);
                break;
            case VELITES:
                card = new Velites(cardResources, notifier);
                break;
                
        }
        
        return card;
    }
    
}
