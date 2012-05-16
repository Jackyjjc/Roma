package model.card;

import framework.cards.Card;
import model.Game;
import model.ICardResources;
import model.IGameIO;
import model.card.behaviour.*;

public class CardFactory {

    private ICardResources cardResources;
    private IGameIO gameIO;
    private Game game;

    public CardFactory(Game game) {

        this.cardResources = (ICardResources) game;
        this.gameIO = (IGameIO) game;
        this.game = game;

    }

    public AbstractCard create(Card name) {

        AbstractCard card = null;

        switch(name) {
            case AESCULAPINUM:
                card = new Aesculapinum(cardResources, gameIO);
                card.setBehaviour(new AesculapinumBehaviour(card));
                break;
            case ARCHITECTUS:
                card = new Architectus(cardResources, gameIO);
                card.setBehaviour(new ArchitectusBehaviour(card));
                break;
            case BASILICA:
                card = new Basilica(cardResources, gameIO);
                card.setBehaviour(new BasilicaBehaviour(card));
                break;
            case CENTURIO:
                card = new Centurio(cardResources, gameIO);
                card.setBehaviour(new CenturioBehaviour(card));
                break;
            case CONSILIARIUS:
                card = new Consiliarius(cardResources, gameIO);
                card.setBehaviour(new ConsiliariusBehaviour(card));
                break;
            case CONSUL:
                card = new Consul(cardResources, gameIO);
                card.setBehaviour(new ConsulBehaviour(card));
                break;
            case ESSEDUM:
                card = new Essedum(cardResources, gameIO);
                card.setBehaviour(new EssedumBehaviour(card,game.getTurnMover()));
                break;
            case FORUM:
                card = new Forum(cardResources, gameIO);
                card.setBehaviour(new ForumBehaviour(card));
                break;
            case GLADIATOR:
                card = new Gladiator(cardResources, gameIO);
                card.setBehaviour(new GladiatorBehaviour(card));
                break;
            case HARUSPEX:
                card = new Haruspex(cardResources, gameIO);
                card.setBehaviour(new HaruspexBehaviour(card));
                break;
            case LEGAT:
                card = new Legat(cardResources, gameIO);
                card.setBehaviour(new LegatBehaviour(card));
                break;
            case LEGIONARIUS:
                card = new Legionarius(cardResources, gameIO);
                card.setBehaviour(new LegionariusBehaviour(card));
                break;
            case MACHINA:
                card = new Machina(cardResources, gameIO);
                card.setBehaviour(new MachinaBehaviour(card));
                break;
            case MERCATOR:
                card = new Mercator(cardResources, gameIO);
                card.setBehaviour(new MercatorBehaviour(card));
                break;
            case MERCATUS:
                card = new Mercatus(cardResources, gameIO);
                card.setBehaviour(new MercatusBehaviour(card));
                break;
            case NERO:
                card = new Nero(cardResources, gameIO);
                card.setBehaviour(new NeroBehaviour(card));
                break;
            case NOT_A_CARD:
                break;
            case ONAGER:
                card = new Onager(cardResources, gameIO);
                card.setBehaviour(new OnagerBehaviour(card));
                break;
            case PRAETORIANUS:
                card = new Praetorianus(cardResources, gameIO);
                card.setBehaviour(new PraetorianusBehaviour(card));
                break;
            case SCAENICUS:
                card = new Scaenicus(cardResources, gameIO);
                card.setBehaviour(new ScaenicusBehaviour(card));
                break;
            case SENATOR:
                card = new Senator(cardResources, gameIO);
                card.setBehaviour(new SenatorBehaviour(card));
                break;
            case SICARIUS:
                card = new Sicarius(cardResources, gameIO);
                card.setBehaviour(new SicariusBehaviour(card));
                break;
            case TEMPLUM:
                card = new Templum(cardResources, gameIO);
                card.setBehaviour(new TemplumBehaviour(card));
                break;
            case TELEPHONEBOX:
                card = new TelephoneBox(cardResources, gameIO);
                card.setBehaviour(new TelephoneBoxBehaviour(card, game.getTurnMover()));
                break;
            case TRIBUNUSPLEBIS:
                card = new TribunusPlebis(cardResources, gameIO);
                card.setBehaviour(new TribunusPlebisBehaviour(card));
                break;
            case TURRIS:
                card = new Turris(cardResources, gameIO);
                card.setBehaviour(new TurrisBehaviour(card));
                break;
            case VELITES:
                card = new Velites(cardResources, gameIO);
                card.setBehaviour(new VelitesBehaviour(card));
                break;
            case KAT:
                card = new Kat(cardResources, gameIO);
                card.setBehaviour(new KatBehaviour(card));
                break;
            case GRIMREAPER:
                card = new GrimReaper(cardResources, gameIO);
                card.setBehaviour(new GrimReaperBehaviour(card));
                break;

        }

        return card;
    }

}
