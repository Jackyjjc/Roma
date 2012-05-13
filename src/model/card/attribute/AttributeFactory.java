package model.card.attribute;

import framework.cards.Card;
import model.ICardResources;
import model.IGameIO;

public class AttributeFactory {
	
	public static Attribute createAttributes (Card name, IGameIO gameIO, ICardResources cardResources) {
	
		Attribute cardAttribute = null;
		
		switch(name) {
		case AESCULAPINUM:
			cardAttribute = new Aesculapinum(gameIO,cardResources);
			break;
		case ARCHITECTUS: 
			cardAttribute = new Architectus(gameIO,cardResources);
			break;
		case BASILICA:
			cardAttribute = new Basilica(gameIO, cardResources);
			break;
		case CENTURIO:
			cardAttribute = new Centurio(gameIO, cardResources);
			break;
		case CONSILIARIUS:
			cardAttribute = new Consiliarius(gameIO, cardResources);
			break;
		case CONSUL:
			cardAttribute = new Consul(gameIO, cardResources);
			break;
		case ESSEDUM:
			cardAttribute = new Essedum(gameIO, cardResources);
			break;
		case FORUM:
			cardAttribute = new Forum(gameIO, cardResources);
			break;
		case GLADIATOR:
			cardAttribute = new Gladiator(gameIO, cardResources);
			break;
		case HARUSPEX:
			cardAttribute = new Haruspex(gameIO, cardResources);
			break;
		case LEGAT:
			cardAttribute = new Legat(gameIO, cardResources);
			break;
		case LEGIONARIUS:
			cardAttribute = new Legionarius(gameIO, cardResources);
			break;
		case MACHINA:
			cardAttribute = new Machina(gameIO, cardResources);
			break;
		case MERCATOR:
			cardAttribute = new Mercator(gameIO, cardResources);
			break;
		case MERCATUS:
			cardAttribute = new Mercatus(gameIO, cardResources);
			break;
		case NERO:
			cardAttribute = new Nero(gameIO, cardResources);
			break;
		case NOT_A_CARD:
			break;
		case ONAGER:
			cardAttribute = new Onager(gameIO, cardResources);
			break;
		case PRAETORIANUS:
			cardAttribute = new Praetorianus(gameIO, cardResources);
			break;
		case SCAENICUS:
			cardAttribute = new Scaenicus(gameIO, cardResources);
			break;
		case SENATOR:
			cardAttribute = new Senator(gameIO, cardResources);
			break;
		case SICARIUS:
			cardAttribute = new Sicarius(gameIO, cardResources);
			break;
		case TEMPLUM:
			cardAttribute = new Templum(gameIO, cardResources);
			break;
		case TRIBUNUSPLEBIS:
			cardAttribute = new TribunusPlebis(gameIO, cardResources);
			break;
		case TURRIS:
			cardAttribute = new Turris(gameIO, cardResources);
			break;
		case VELITES:
			cardAttribute = new Velites(gameIO, cardResources);
			break;

		}

		return cardAttribute;

	}

}
