package com.sim;
/* Context class to hold all context relevant to a session.
 */

public class Context {
    int tid = 0;

    Space currentSpace;
    public World world;
    boolean done = false;
    public int balance = 100;
    public Inventory inv = new Inventory();

    Context(World world) {
        this.world = world;
        currentSpace = world.getEntry();

        // Below is an item list.
        Item item1 = new Item("Test","This is a Test Item",true,true);
        Item item2 = new Item("Test2","This is a Test Item",true,true);
        Item item3 = new Item("Test2","This is a Test Item",true,true);
        inv.addItem(item1); // This is only test items. Right now they will be added as soon as the games starts.
        inv.addItem(item2);
        inv.addItem(item3);
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

    public void NextTurn() { /* Method to go to next turn. */
        System.out.println("1 month later...");

        balance += world.roads.size() * 10 + 10;

        System.out.println("Your new balance is " + balance);

        tid++;
        int max = 5;
        if (tid > max) {
            System.out.println("you lose");
        }

        int antalveje = 10;
        if (antalveje == world.roads.size()) {
            System.out.println("you win");

        }
    }
    public void GetBalance() {
        System.out.println("Your balance is " + balance);

    }

    public int getPrice(CitySpace city1, CitySpace city2) {
        return 50; // TODO
    }




}



