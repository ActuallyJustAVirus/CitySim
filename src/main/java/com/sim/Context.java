package com.sim;
/* Context class to hold all context relevant to a session.
 */

import java.text.DecimalFormat;
import java.io.File;

public class Context {
    public int tid = 0;
    public int max = 72;   //12 months * 6 years = 72 max rounds.
    public int antalveje = 10;
    int points = 0;
    Space currentSpace;
    public World world;
    boolean done = false;
    public double balance = 100;
    public Inventory inv = new Inventory();
    public String[] gameMonth = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public int gameYear = 2024;

    public Context(World world) {
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

    public void NextTurn() { /* Method to go to next turn. */
        System.out.println("1 month later...");
        int pointsThisRound = 0;
        for (CitySpace city : world.spaces) {
            pointsThisRound += city.getPoints();
        }
        points += pointsThisRound;
        System.out.println("You got " + pointsThisRound + " points this round.");
        System.out.println("You have " + points + " points in total.");

        // calculate balance
        double moneyThisRound = pointsThisRound / 100 + 5;
        DecimalFormat Rounded = new DecimalFormat("0.00");
        String formattedPrice = Rounded.format(moneyThisRound);
        formattedPrice = formattedPrice.replace(",", ".");
        moneyThisRound = Double.parseDouble(formattedPrice);
        balance += moneyThisRound;

        System.out.println("Your new balance is " + balance);

        tid++;

        if (tid % 12 == 0){
            gameYear++;
        }
        else if (tid > max) {
                System.out.println("you lose");
        }



        // if (antalveje == world.roads.size()) {
        //     System.out.println("you win");

        // }
    }
    public void GetBalance() {
        System.out.println("Your balance is " + balance);

    }

    public String getGameTime(){
        return(gameMonth[(tid % 12)] + " " + gameYear);

    }

    public double getPrice(CitySpace city1, CitySpace city2) {
        double cityDistances = Math.sqrt(Math.pow(city2.getX() - city1.getX(),2) + Math.pow((city2.getY() - city1.getY())*4,2));
        double Price = cityDistances * 2.3;

        DecimalFormat Rounded = new DecimalFormat("#.##");
        // Convert the formatted price string to a double using Double.parseDouble()
        String formattedPrice = Rounded.format(Price);
        formattedPrice = formattedPrice.replace(",", ".");
        return Double.parseDouble(formattedPrice);
    }
    public boolean hasTools(String tool){
        if (inv.getItem(tool) != null){
            return true;
        }
        else {
            return false;
        }
    }




}