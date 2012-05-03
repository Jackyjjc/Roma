package model;

import model.runner.AcceptanceTestImplementation;

public class Tester {

    private static Test[] tests = {
        new TurnAdvanceTest(),
        new GetMoneyTest(),
        new GetCardsTest(),
    };
    
    public static void main(String[] args) {
        
        AcceptanceTestImplementation ai = new AcceptanceTestImplementation();
        
        for(int i = 0; i < tests.length; i++) {
            
            GameState state = ai.getInitialState();
            
            tests[i].run(state, ai.getMover(state));
        }
        
        System.out.println("All test passed!! You are AWESOME!!!!!");
    }
    
}
