package gui;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ResourceManager {

    public BufferedImage icon;
    
    public BufferedImage background;
    public BufferedImage hand;
    
    public BufferedImage player1;
    public BufferedImage player2;
    
    public BufferedImage stockpile;
    
    public BufferedImage d1;
    public BufferedImage d2;
    public BufferedImage d3;
    public BufferedImage d4;
    public BufferedImage d5;
    public BufferedImage d6;
    
    public BufferedImage bd1;
    public BufferedImage bd2;
    public BufferedImage bd3;
    public BufferedImage bd4;
    public BufferedImage bd5;
    public BufferedImage bd6;
    
    public BufferedImage disc1;
    public BufferedImage disc2;
    public BufferedImage disc3;
    public BufferedImage disc4;
    public BufferedImage disc5;
    public BufferedImage disc6;
    public BufferedImage disc7;
    public BufferedImage disc8;
    
    public BufferedImage blank;
    public BufferedImage blankh;
    public BufferedImage card;
    public BufferedImage cardh;
    public BufferedImage aesculapinum;
    public BufferedImage architectus;
    public BufferedImage basilica;
    public BufferedImage centurio;
    public BufferedImage consiliarus;
    public BufferedImage consul;
    public BufferedImage essedum;
    public BufferedImage forum;
    public BufferedImage gladiator;
    public BufferedImage haruspex;
    public BufferedImage legat;
    public BufferedImage legionarius;
    public BufferedImage machina;
    public BufferedImage meractor;
    public BufferedImage mercatus;
    public BufferedImage nero;
    public BufferedImage onager;
    public BufferedImage praetoranius;
    public BufferedImage scaenicus;
    public BufferedImage senator;
    public BufferedImage sicarius;
    public BufferedImage templum;
    public BufferedImage tribunisplebis;
    public BufferedImage turris;
    public BufferedImage velites;
    
    private double scalingFactor;
    
    public ResourceManager(double scalingFactor) {
        this.scalingFactor = scalingFactor;
        load();
    }
    
    private void load() {
        
        try {
            
            //load icon
            icon = read("icon.png");
            
            //load background
            background = read("background.jpg");
            
            //load hand
            hand = read("hand.png");
            
            //load stockpile
            stockpile = read("vp.png");
            
            //load player
            player1 = read("p1bar.png");
            player2 = read("p2bar.png");
            
            //load all the action die
            d1 = read("d1.png");
            d2 = read("d2.png");
            d3 = read("d3.png");
            d4 = read("d4.png");
            d5 = read("d5.png");
            d6 = read("d6.png");
            
            //load all the battle die
            bd1 = read("bd1.png");
            bd2 = read("bd2.png");
            bd3 = read("bd3.png");
            bd4 = read("bd4.png");
            bd5 = read("bd5.png");
            bd6 = read("bd6.png");
            
            //load all the discs
            disc1 = read("disc1.jpg");
            disc2 = read("disc2.jpg");
            disc3 = read("disc3.jpg");
            disc4 = read("disc4.jpg");
            disc5 = read("disc5.jpg");
            disc6 = read("disc6.jpg");
            disc7 = read("disc7.jpg");
            disc8 = read("disc8.jpg");
            
            //load all the cards
            blank = read("empty.png");
            blankh = read("empty-h.png");
            card = read("CardBack.jpg");
            cardh = read("CardBack-h.jpg");
            
            aesculapinum = read("CardAesculapinum.jpg");
            architectus = read("CardArchitectus.jpg");
            basilica = read("CardBasilica.jpg");
            centurio = read("CardCenturio.jpg");
            consiliarus = read("CardConsiliarius.jpg");
            consul = read("CardConsul.jpg");
            essedum = read("CardEssedum.jpg");
            forum = read("CardForum.jpg");
            gladiator = read("CardGladiator.jpg");
            haruspex = read("CardHaruspex.jpg");
            legat = read("CardLegat.jpg");
            legionarius = read("CardLegionarius.jpg");
            machina = read("CardMachina.jpg");
            meractor = read("CardMarcator.jpg");
            mercatus = read("CardMercatus.jpg");
            nero = read("CardNero.jpg");
            onager = read("CardOnager.jpg");
            praetoranius = read("CardPraetorianus.jpg");
            scaenicus = read("CardScaenicus.jpg");
            senator = read("CardSenator.jpg");
            sicarius = read("CardSicarius.jpg");
            templum = read("CardTemplum.jpg");
            tribunisplebis = read("CardTribunusPlebis.jpg");
            turris = read("CardTurris.jpg");
            velites = read("CardVelites.jpg");
            
        } catch (IOException e) {
            System.err.println("Sorry Game has been corrupted.");
            System.exit(1);
        }
    }
    
    private BufferedImage read(String name) throws IOException {
        
        BufferedImage origin = ImageIO.read(ResourceManager.class.getResource("/resource/" + name));
        
        return scale(origin);
    }
    
    private BufferedImage scale(BufferedImage original) {
        
        /*
         * Credit is given to http://stackoverflow.com/questions/4216123/how-to-scale-a-bufferedimage
         */
        
        int width = new Double(original.getWidth() * scalingFactor).intValue();
        int height = new Double(original.getHeight() * scalingFactor).intValue();
        
        BufferedImage resized = new BufferedImage(width, height, original.getType());
        Graphics2D g = resized.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(original, 0, 0, width, height, 0, 0, original.getWidth(), original.getHeight(), null);
        g.dispose();
        
        return resized;
    }
}
