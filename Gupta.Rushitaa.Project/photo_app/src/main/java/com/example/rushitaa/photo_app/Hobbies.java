package com.example.rushitaa.photo_app;

/**
 * Created by Rushitaa on 2/22/2015.
 */
public class Hobbies {

    protected String h_Name;
    boolean selected;

    Hobbies( String h,boolean selected)
    {
        super();
        h_Name = h;
        this.selected=selected;

    }

    public String getName(){
        return h_Name;
    }
    public void setName(String name) {
        h_Name = name;
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}

