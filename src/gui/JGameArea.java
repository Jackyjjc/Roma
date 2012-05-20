package gui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.IGameDisplayState;

public class JGameArea extends JPanel implements IListener {
    
    private IDisplayManager idm;
    
    private JHand hand0;
    private JHand hand1;
    private JField field0;
    private JField field1;
    private JDiscList discs;
    
    public JGameArea(IDisplayManager idm) {
        
        this.idm = idm;
        
        createElements();
        initUI();
        setOpaque(false);
    }

    public void updateView(IGameDisplayState state) {
        
        hand0.updateView(state);
        hand1.updateView(state);
        field0.updateView(state);
        field1.updateView(state);
        
    }
    
    private void createElements() {
        
        hand0 = new JHand(0, idm);
        hand1 = new JHand(1,idm);
        field0 = new JField(0, idm);
        field1 = new JField(1, idm);
        discs = new JDiscList(idm);
    }
    
    private void initUI() {
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(hand1);
        add(field1);
        add(discs);
        add(field0);
        add(hand0);
    }

    public JHand getHand() {
        return hand0;
    }
    
}
