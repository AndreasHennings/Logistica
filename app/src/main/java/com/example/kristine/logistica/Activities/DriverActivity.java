package com.example.kristine.logistica.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kristine.logistica.Database.MyDatabaseAdapter;
import com.example.kristine.logistica.JavaClasses.Driver;
import com.example.kristine.logistica.JavaClasses.Task;
import com.example.kristine.logistica.R;

import java.util.ArrayList;

public class DriverActivity extends AppCompatActivity
{

    private TextView source;
    private TextView target;
    private TextView id;
    private Button button;
    private RelativeLayout layout;
    private Driver currentDriver;


    private Task task;
    private MyDatabaseAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        initComponents();
        setClickListener();

    }

    private void setClickListener()
    {
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (task.getStatus() == 0)
                {
                    driverAcceptsTask();
                }

                else if (task.getStatus() == 1)
                {
                    finishedTask();
                }

                else if (task.getStatus() == 2)
                {
                    button.setEnabled(false);
                }

            }


        });
    }

    private void finishedTask()
    {
        layout.setBackgroundColor(getResources().getColor(R.color.delivered));
        task.updateStatus();
        db.updateTask(task);
        currentDriver.setY_pos(task.getTarget_y());
        currentDriver.setX_pos(task.getTarget_x());
        db.updateDriver(currentDriver);
    }

    private void driverAcceptsTask()
    {
        layout.setBackgroundColor(getResources().getColor(R.color.in_process));
        task.updateStatus();
        db.updateTask(task);
    }

    private void initComponents()
    {
        source = (TextView) findViewById(R.id.source);
        target = (TextView) findViewById(R.id.target);
        id = (TextView) findViewById(R.id.id);
        button = (Button) findViewById(R.id.button);
        layout = (RelativeLayout) findViewById(R.id.layout);

        db = new MyDatabaseAdapter(this);
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        int driverId = (int) extra.get("driver_id");

        currentDriver = null;
        ArrayList<Driver> allDrivers = db.getAllDriver();

        for (int i = 0; i < allDrivers.size(); i++)
        {
            if (allDrivers.get(i).getId() == driverId)
            {
                currentDriver = allDrivers.get(i);
                break;
            }
        }
        task = db.getNearestTask(currentDriver);
        showTask();
    }

    private void showTask()
    {
        id.setText("Task-ID: " + task.getId());
        source.setText("Pickup-adress: X:" + task.getSource_x() + "  Y:" + task.getSource_y());
        target.setText("Delivery-adress: X:" + task.getTarget_x() + "  Y:" + task.getTarget_y());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.driver_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int menuId = item.getItemId();
        if (task.getStatus() == 2)
        {
            if (menuId == R.id.new_task)
            {
                task = db.getNearestTask(currentDriver);
                layout.setBackgroundColor(getResources().getColor(R.color.to_pick_up));
                button.setEnabled(true);
            }

        } else
        {
            Toast toast = Toast.makeText(DriverActivity.this, "You haven´t finished your task!", Toast.LENGTH_LONG);
            toast.show();
        }
        return true;
    }


}
