package model;

import model.runner.AcceptanceTestImplementation;
import framework.interfaces.GameState;

public class Tester {

    private static Test[] tests = {
        new InitialisationCardDiscBasicTest(),
        new InitialisationPlayerBasicTest(),
        new MoverEndTurnTest(),
        new InitialisationBasicSestertiiTest(),
        new InitialisationBasicVictoryPointTest(),
        new TurnAdvanceTest(),
        new GetMoneyTest(),
        new GetCardsTest(),
        new CardActivatorAesculapinumBasicTest(),
        new CardActivatorArchitectusBasicTest(),
        new CardActivatorLegatBasicTest(),
        new CardActivatorSenatorBasicTest(),
        new CardActivatorTribunusPlebisBasicTest(),
        new CardActivatorSenatorBasicTest(),
        new CardActivatorSicariusBasicTest(),
        new CardActivatorScaenicusMimicLegatTest(),
    };
    
    public static void main(String[] args) {
        
        AcceptanceTestImplementation ai = new AcceptanceTestImplementation();
        
        for(int i = 0; i < tests.length; i++) {
            
            GameState state = ai.getInitialState();
            
            System.out.println(tests[i].getShortDescription());
            
            tests[i].run(state, ai.getMover(state));
        }
        
        System.out.println("All test passed!! You are AWESOME!!!!!");
    }
    
}
