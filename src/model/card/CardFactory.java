package model.card;

import model.DiceManager;
import model.ICardStorage;
import model.IResourceStorage;
import model.Notifier;

public class CardFactory {
    
    private IResourceStorage bank;
    private ICardStorage deck;
    private ICardStorage grave;
    
    private Notifier notifier;
    private DiceManager diceManager;
    
    public CardFactory(IResourceStorage bank, Notifier notifier,
                       DiceManager diceManager) {
        
        this.notifier = notifier;
        this.diceManager = diceManager;
        this.bank = bank;
        
    }
    
    public AbstractCard create(Card name) {
        
        AbstractCard card = null;
        
        switch(name) {
            case AESCULAPINUM:
                card = new Aesculapinum(grave, notifier);
                break;
            case ARCHITECTUS: 
                card = new Architectus(grave, notifier);
                break;
            case BASILICA:
                card = new Basilica(bank, grave, notifier);
                break;
            case CENTURIO:
                card = new Centurio(diceManager, grave, notifier);
                break;
            case CONSILIARUS:
                card = new Consiliarius(grave, notifier);
                break;
            case CONSUL:
                card = new Consul(grave, notifier, diceManager);
                break;
            case ESSEDUM:
                card = new Essedum(grave, notifier);
                break;
            case FORUM:
                card = new Forum(grave, notifier, bank, diceManager);
                break;
            case GLADIATOR:
                card = new Gladiator(grave, notifier);
                break;
            case HARUSPEX:
                card = new Haruspex(deck, grave, notifier);
                break;
            case LEGAT:
                card = new Legat(bank, grave, notifier);
                break;
            case LEGIONARIUS:
                card = new Legionarius(diceManager, grave, notifier);
                break;
            case MACHINA:
                card = new Machina(grave, notifier);
                break;
            case MERCATOR:
                card = new Mercator(grave, notifier);
                break;
            case MERCATUS:
                card = new Mercatus(grave, notifier);
                break;
            case NERO:
                card = new Nero(grave, notifier);
                break;
            case NOT_A_CARD:
                break;
            case ONAGER:
                card = new Onager(grave, notifier);
                break;
            case PRAETORIANUS:
                card = new Praetorianus(grave, notifier);
                break;
            case SCAENICUS:
                card = new Scaenicus(grave, this, notifier);
                break;
            case SENATOR:
                card = new Senator(grave, notifier);
                break;
            case SICARIUS:
                card = new Sicarius(grave, notifier);
                break;
            case TEMPLUM:
                card = new Templum(grave, notifier, bank,diceManager);
                break;
            case TRIBUNUSPLEBIS:
                card = new TribunusPlebis(grave, notifier);
                break;
            case TURRIS:
                card = new Turris(grave, notifier);
                break;
            case VELITES:
                card = new Velites(grave, notifier);
                break;
                
        }
        
        return card;
    }
    
}
