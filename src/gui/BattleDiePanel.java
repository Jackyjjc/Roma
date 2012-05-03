package gui;

import gui.DieDisplayManager.Type;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import model.IGameDisplayState;

public class BattleDiePanel extends JPanel implements IListener {
    
    private JDie battleDie;
    
    public BattleDiePanel(ResourceManager rm) {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        
        initUI(new DieDisplayManager(rm, Type.BATTLE));
        setOpaque(false);
    }

    private void initUI(DieDisplayManager ddm) {
        
        battleDie = new JDie(0,ddm);
        add(battleDie);
    }

    public void updateView(IGameDisplayState state) {
        battleDie.setValue(6);
    }
    
}
