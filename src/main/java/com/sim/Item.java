package com.sim;

public class Item {
    String description;
    String name;
    boolean portable;
    boolean listable;

    public Item(String name, String desc, boolean listable, boolean portable){
        this.name = name;
        this.description = desc;
        this.listable = listable;
        this.portable = portable;
    }

    public String getName(){
        return this.name;
    }

    public String getDesc(){
        if(this.description.isEmpty()){
            return "There is no information about this item";
        } else {
            return this.description;
        }
    }
    public void Inspect(){
        System.out.println(("You examine the "+this.getName()+". "+this.getDesc()));
    }
}
