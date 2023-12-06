package com.sim;

import java.io.File;

public class Item {
    String description;
    String name;
    public File image;

    public Item(String name, String desc, File image){
        this.name = name;
        this.description = desc;
        this.image = image;
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
    public void inspect(){
        System.out.println(("You examine the "+this.getName()+". "+this.getDesc()));
    }
}
