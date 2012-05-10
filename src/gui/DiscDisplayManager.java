package gui;

import java.awt.image.BufferedImage;

public class DiscDisplayManager {

    private static final int NUM_DISCS = 8;
    private BufferedImage[] mapping;
    private int width;
    private int height;
    
    public DiscDisplayManager(ResourceManager rm) {
        setWidth(rm.disc2.getWidth());
        setHeight(rm.disc2.getHeight());
        initMapping(rm);
    }
    
    private void initMapping(ResourceManager rm) {
        
        mapping = new BufferedImage[NUM_DISCS];
        
        mapping[0] = rm.disc1;
        mapping[1] = rm.disc2;
        mapping[2] = rm.disc3;
        mapping[3] = rm.disc4;
        mapping[4] = rm.disc5;
        mapping[5] = rm.disc6;
        mapping[6] = rm.disc7;
        mapping[7] = rm.disc8;
    }

    public BufferedImage getDisc(int value) {
        return mapping[value];
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
}
