package gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.IGameDisplayState;

public class JStockPile extends JPanel implements IListener {

    public JStockPile(ResourceManager rm) {
        JLabel label = new JLabel(new ImageIcon(rm.stockpile));
        add(label);
        setOpaque(false);
    }

    public void updateView(IGameDisplayState state) {
        // TODO Auto-generated method stub
        
    }
    
}
