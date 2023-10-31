package game;
/* Context class to hold all context relevant to a session.
 */

public class Context {
 
    int tidilbage =5;
    int tid= 0;
    

    Space currentSpace;
    public World world;
    boolean done = false;

    int balance = 6969;

    Context(World world) {
        this.world = world;
        currentSpace = world.getEntry();
    }

    public Space getCurrentSpace() {
        return currentSpace;
    }

    public void transition(String direction) {
        Space nextSpace = currentSpace.followEdge(direction);
        if (nextSpace == null) {
            System.out.println("You are confused, and walk in a circle looking for '" + direction
                    + "'. In the end you give up 😩");
        } else {
            currentSpace.goodbye();
            currentSpace = nextSpace;
            currentSpace.welcome();
        }
    }

    public void makeDone() {
        done = true;
    }

    public boolean isDone() {
        return done;
    }

    public void NextTurn (){  /*Method to go to next turn. */
        System.out.println("´1 month later...");
        System.out.println("Your new balance is "+balance);
        balance += 123456;
        tid++;
        int max = 5;
        if (tid > max) {
           System.out.println("you lose");               
        }

    int antalveje=10;
    if(antalveje==world.roads.size()){
        System.out.println("you win");

    }
    
        
    }
    
    public void GetBalance(){
    System.out.println("Your balance is "+balance);

    }
}






