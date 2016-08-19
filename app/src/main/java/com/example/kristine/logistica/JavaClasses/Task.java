package com.example.kristine.logistica.JavaClasses;

/**
 * Created by Kristine on 06.08.2016.
 */
public class Task {
    private int id;
    private int source_x;
    private int source_y;
    private int target_x;
    private int target_y;
    private int status;
    private String time;
    private String date;

    public Task(int id, int source_x, int source_y, int target_x, int target_y,String date, String time){
        this.id=id;
        this.source_x=source_x;
        this.source_y=source_y;
        this.target_x=target_x;
        this.target_y=target_y;
        this.status=0;
        this.date=date;
        this.time=time;
    }

    public int getId()
    {
        return id;
    }

    public int getSource_x(){
        return source_x;
    }

    public int getSource_y(){return source_y;
    }

    public int getTarget_x(){return target_x;}

    public int getTarget_y(){return target_y;}

    public String getTime(){
        return time;
    }

    public String getDate() {
        return date;
    }

    public void updateStatus(){
        if(this.getStatus()<2)
        {
            this.status++;
        }
    }

    public int getStatus(){
        return status;
    }
}

