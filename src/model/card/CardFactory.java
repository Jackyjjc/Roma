package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

public class CardFactory {
    
    private ICardResources cardResources;
    private IGameIO gameIO;
    
    public CardFactory(ICardResources cardResources, IGameIO gameIO) {
        
        this.cardResources = cardResources;
        this.gameIO = gameIO;
        
    }
    
    public AbstractCard create(Card name) {
        
        AbstractCard card = null;
        
        switch(name) {
            case AESCULAPINUM:
                card = new Aesculapinum(cardResources, gameIO);
                break;
            case ARCHITECTUS: 
                card = new Architectus(cardResources, gameIO);
                break;
            case BASILICA:
                card = new Basilica(cardResources, gameIO);
                break;
            case CENTURIO:
                card = new Centurio(cardResources, gameIO);
                break;
            case CONSILIARUS:
                card = new Consiliarius(cardResources, gameIO);
                break;
            case CONSUL:
                card = new Consul(cardResources, gameIO);
                break;
            case ESSEDUM:
                card = new Essedum(cardResources, gameIO);
                break;
            case FORUM:
                card = new Forum(cardResources, gameIO);
                break;
            case GLADIATOR:
                card = new Gladiator(cardResources, gameIO);
                break;
            case HARUSPEX:
                card = new Haruspex(cardResources, gameIO);
                break;
            case LEGAT:
                card = new Legat(cardResources, gameIO);
                break;
            case LEGIONARIUS:
                card = new Legionarius(cardResources, gameIO);
                break;
            case MACHINA:
                card = new Machina(cardResources, gameIO);
                break;
            case MERCATOR:
                card = new Mercator(cardResources, gameIO);
                break;
            case MERCATUS:
                card = new Mercatus(cardResources, gameIO);
                break;
            case NERO:
                card = new Nero(cardResources, gameIO);
                break;
            case NOT_A_CARD:
                break;
            case ONAGER:
                card = new Onager(cardResources, gameIO);
                break;
            case PRAETORIANUS:
                card = new Praetorianus(cardResources, gameIO);
                break;
            case SCAENICUS:
                card = new Scaenicus(cardResources, this, gameIO);
                break;
            case SENATOR:
                card = new Senator(cardResources, gameIO);
                break;
            case SICARIUS:
                card = new Sicarius(cardResources, gameIO);
                break;
            case TEMPLUM:
                card = new Templum(cardResources, gameIO);
                break;
            case TRIBUNUSPLEBIS:
                card = new TribunusPlebis(cardResources, gameIO);
                break;
            case TURRIS:
                card = new Turris(cardResources, gameIO);
                break;
            case VELITES:
                card = new Velites(cardResources, gameIO);
                break;
                
        }
        
        return card;
    }
    
}
