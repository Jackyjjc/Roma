package gui;

import java.awt.image.BufferedImage;

public class DieDisplayManager {

    public enum Type {
        ACTION, BATTLE
    }
    
    private static final int NUM_DICE = 6;
    private BufferedImage[] mapping;
    private int width;
    private int height;
    
    public DieDisplayManager(ResourceManager rm, Type type) {
        setWidth(rm.d1.getWidth());
        setHeight(rm.d1.getHeight());
        initMapping(rm, type);
    }
    
    private void initMapping(ResourceManager rm, Type type) {
        
        mapping = new BufferedImage[NUM_DICE];
        
        if(type == Type.ACTION) {
            mapping[0] = rm.d1;
            mapping[1] = rm.d2;
            mapping[2] = rm.d3;
            mapping[3] = rm.d4;
            mapping[4] = rm.d5;
            mapping[5] = rm.d6;
        } else {
            mapping[0] = rm.bd1;
            mapping[1] = rm.bd2;
            mapping[2] = rm.bd3;
            mapping[3] = rm.bd4;
            mapping[4] = rm.bd5;
            mapping[5] = rm.bd6;
        }
    }

    public BufferedImage getDie(int value) {
        return mapping[value - 1];
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
