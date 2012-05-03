package gui;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JDie extends JButton {
    
    private DieDisplayManager ddm;
    private int index;
    private int value;
    
    public JDie(int index, DieDisplayManager ddm) {
        
        this.index = index;
        this.ddm = ddm;
        
        setPreferredSize(new Dimension(ddm.getWidth(), ddm.getHeight()));
        setValue(6);
    }
    
    public void setValue(int value) {
        this.value = value;
        this.setIcon(new ImageIcon(ddm.getDie(value)));
    }
    
    public int getIndex() {
        return index;
    }
    
    public int getValue() {
        return value;
    }
}