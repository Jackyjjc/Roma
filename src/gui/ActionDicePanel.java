package gui;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import model.IGameDisplayState;
import controller.CustomizedTransferHandler;
import controller.MouseDraggingAdapter;

@SuppressWarnings("serial")
public class ActionDicePanel extends JPanel implements IListener {
    
    private static final int NUM_DICES = 3;
    private JDie[] dice;
    private MouseDraggingAdapter[] adapters;
    
    public ActionDicePanel(IDisplayManager idm) {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, idm.scale(15), 0));
        initUI(idm);
        setOpaque(false);
    }

    private void initUI(IDisplayManager idm) {
        
        dice = new JDie[NUM_DICES];
        adapters = new MouseDraggingAdapter[NUM_DICES];
        
        for(int i = 0; i < NUM_DICES; i++) {
            dice[i] = new JDie(i,idm.getActionDiceDisplayManager());
            adapters[i] = new MouseDraggingAdapter(idm.getInputHandler());
            dice[i].addMouseListener(adapters[i]);
            dice[i].setTransferHandler(new CustomizedTransferHandler());
            add(dice[i]);
        }
    }

    public void updateView(IGameDisplayState state) {
        
        int[] actionDice = state.getActionDice();
        
        for(int i = 0; i < actionDice.length; i++) {
            dice[i].setValue(actionDice[i]);
            dice[i].setVisible(true);
        }
        
        for(int i = actionDice.length; i < NUM_DICES; i++) {
            dice[i].setVisible(false);
        }
        
        revalidate();
    }
    
    public void enableAdapters(boolean enable) {
        for(int i = 0; i < NUM_DICES; i++) {
            adapters[i].setDraggable(enable);
        }
    }

}
