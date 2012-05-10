package unittests;

public class Tester {

    public static ITest[] tests = new ITest[] {
        new DiceManagerTests(),
    };
    
    public static void main(String[] args) {
        
        for(ITest test : tests) {
            test.run();
        }
        
        System.out.println("All tests passed!! You are awesome!!!");
    }
    
}
