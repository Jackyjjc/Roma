package gui;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import framework.cards.Card;


public class CardDisplayManager {
    
    private ResourceManager rm;
    
    private int width;
    private int height;
    
    Map<Card,BufferedImage> maps;
    
    public CardDisplayManager(ResourceManager rm) {
        
        this.rm = rm;
        
        setHeight(rm.sicarius.getHeight());
        setWidth(rm.sicarius.getWidth());
        maps = new HashMap<Card, BufferedImage>();
        initMapping();
    }

    private void initMapping() {
        maps.put(Card.AESCULAPINUM, rm.aesculapinum);
        maps.put(Card.ARCHITECTUS, rm.architectus);
        maps.put(Card.BASILICA, rm.basilica);
        maps.put(Card.CENTURIO, rm.centurio);
        maps.put(Card.CONSILIARIUS, rm.consiliarus);
        maps.put(Card.CONSUL, rm.consul);
        maps.put(Card.ESSEDUM, rm.essedum);
        maps.put(Card.FORUM, rm.forum);
        maps.put(Card.GLADIATOR, rm.gladiator);
        maps.put(Card.HARUSPEX, rm.haruspex);
        maps.put(Card.LEGAT, rm.legat);
        maps.put(Card.LEGIONARIUS, rm.legionarius);
        maps.put(Card.MACHINA, rm.machina);
        maps.put(Card.MERCATOR, rm.meractor);
        maps.put(Card.MERCATUS, rm.mercatus);
        maps.put(Card.NERO, rm.nero);
        maps.put(Card.NOT_A_CARD, rm.blank);
        maps.put(Card.ONAGER, rm.onager);
        maps.put(Card.PRAETORIANUS, rm.praetoranius);
        maps.put(Card.SCAENICUS, rm.scaenicus);
        maps.put(Card.SENATOR, rm.senator);
        maps.put(Card.SICARIUS, rm.sicarius);
        maps.put(Card.TEMPLUM, rm.templum);
        maps.put(Card.TRIBUNUSPLEBIS, rm.tribunisplebis);
        maps.put(Card.TURRIS, rm.turris);
        maps.put(Card.VELITES, rm.velites);
        maps.put(Card.KAT, rm.kat);
        maps.put(Card.GRIMREAPER, rm.grimreaper);
        maps.put(Card.TELEPHONEBOX, rm.telephonebox);
    }
    
    public BufferedImage getFaceDownCard() {
        return rm.card;
    }
    
    public BufferedImage getCard(Card card) {
        
        BufferedImage img = rm.blank;
        
        if(card != null) {
            img = maps.get(card);
        }
        
        return img;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int wIDTH) {
        width = wIDTH;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int hEIGHT) {
        height = hEIGHT;
    }
    

}
