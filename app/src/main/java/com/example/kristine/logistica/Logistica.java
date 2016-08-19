package com.example.kristine.logistica;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kristine.logistica.Activities.AdminActivity;
import com.example.kristine.logistica.Activities.DriverActivity;
import com.example.kristine.logistica.Database.MyDatabaseAdapter;
import com.example.kristine.logistica.JavaClasses.Driver;
import com.example.kristine.logistica.JavaClasses.Task;

import java.util.ArrayList;

public class Logistica extends AppCompatActivity
{

    private EditText driverID;
    private Button input;
    private Button manager;

    private MyDatabaseAdapter db;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_logistica);
        setActionBarInActivity();
        initUI();
        initComponents();
        setClickListener();
        create3Drivers();
        create5Tasks();

    }
    private void initUI()
    {
        driverID=(EditText) findViewById(R.id.editText);
        input=(Button)findViewById(R.id.okbutton);
        manager=(Button)findViewById(R.id.manager_options);
    }

    private void create5Tasks()
    {
        ArrayList<Task> allTasks = db.getAllTask();
        if (allTasks.size() == 0) {
            db.addTask(11, 42, 36, 21, "12.08.2016", "14:04");
            db.addTask(25, 31, 52, 66, "16.08.2016", "20:34");
            db.addTask(32, 55, 15, 22, "16.08.2016", "21:20");
            db.addTask(41, 52, 32, 33, "17.08.2016", "10:00");
            db.addTask(22, 35, 18, 44, "17.08.2016", "10:24");
        }
    }

    private void create3Drivers()
    {
        ArrayList<Driver> allDrivers=db.getAllDriver();
        if (allDrivers.size()==0) {
            db.addDriver("Peter", 10, 20);
            db.addDriver("Wolfgang", 22, 38);
            db.addDriver("Sabine", 37, 30);
        }
    }

    private void setActionBarInActivity()
    {
        ActionBar actionBar=getSupportActionBar();
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    private void initComponents()
    {

        db=new MyDatabaseAdapter(this);

    }

    private void setClickListener()
    {
       input.setOnClickListener(new View.OnClickListener()
       {
            @Override
            public void onClick(View v)
            {
                int id=Integer.parseInt(driverID.getText().toString());
                if (id>0 && id<=db.getAllDriver().size())
                {
                    Intent intent = new Intent(Logistica.this, DriverActivity.class);
                    intent.putExtra("driver_id", id);
                    startActivity(intent);
                }
                else
                {
                    driverID.setText("");

                }
            }

        });

        manager.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(Logistica.this, AdminActivity.class);
                startActivity(intent);
            }
        });
    }
}