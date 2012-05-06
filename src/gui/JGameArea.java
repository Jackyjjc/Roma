package gui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.IGameDisplayState;
import model.InputHandler;
import controller.DiscClickListener;
import controller.FieldClickListener;
import controller.HandClickListener;

public class JGameArea extends JPanel implements IListener {
    
    private ResourceManager rm;
    private CardDisplayManager cdm;
    
    private JHand hand0;
    private JHand hand1;
    private JField field0;
    private JField field1;
    private JDiscList discs;
    
    public JGameArea(ResourceManager rm, CardDisplayManager cdm, InputHandler handler) {
        
        this.rm = rm;
        this.cdm = cdm;
        
        createElements(handler);
        initUI();
        setOpaque(false);
    }

    public void updateView(IGameDisplayState state) {
        hand0.updateView(state);
        hand1.updateView(state);
        field0.updateView(state);
        field1.updateView(state);
        
    }
    
    private void createElements(InputHandler handler) {
        
        FieldClickListener fListener = new FieldClickListener(handler);
        HandClickListener hListener = new HandClickListener(handler);
        DiscClickListener dListener = new DiscClickListener(handler);
        
        hand0 = new JHand(0, cdm, hListener);
        hand1 = new JHand(1,cdm, hListener);
        field0 = new JField(0,cdm, fListener);
        field1 = new JField(1,cdm, fListener);
        discs = new JDiscList(rm, dListener);
    }
    
    private void initUI() {
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(hand1);
        add(field1);
        add(discs);
        add(field0);
        add(hand0);
    }

}
