package com.example.kristine.logistica.JavaClasses;

/**
 * Created by Kristine on 06.08.2016.
 */
public class Driver {
    private int id;
    private String name;
    private int x_pos;
    private int y_pos;

    public Driver(int id, String name, int x_pos, int y_pos){
        this.id=id;
        this.name=name;
        this.x_pos=x_pos;
        this.y_pos=y_pos;
    }
    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getX_pos(){
        return x_pos;
    }

    public void setX_pos(int x){
        this.x_pos=x;
    }

    public void setY_pos(int y){this.y_pos=y;}

    public int getY_pos(){
        return y_pos;
    }
}
