package gui;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JDisc extends JButton {
    
    private DiscDisplayManager ddm;
    private int index;

    public JDisc(DiscDisplayManager ddm, int i) {
        this.ddm = ddm;
        setIndex(i);
        setPreferredSize(new Dimension(ddm.getWidth(),ddm.getHeight()));
    }
    
    private void setIndex(int index) {
        this.index = index;
        setIcon(new ImageIcon(ddm.getDisc(index)));
    }
    
    public int getIndex() {
        return index;
    }
}
