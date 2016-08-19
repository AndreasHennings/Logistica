package com.example.kristine.logistica.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.kristine.logistica.Logistica;
import com.example.kristine.logistica.R;

public class AdminActivity extends AppCompatActivity {

    Button addDriver;
    Button addTask;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        setActionBarInActivity();
        initUI();
        setClickListener();




    }
    private void setActionBarInActivity() {
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
    }

    private void setClickListener() {
        addDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AllDriver.class);
                startActivity(intent);
            }
        });

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AllTasks.class);
                startActivity(intent);
            }
        });

    }

    private void initUI() {
        addDriver = (Button)findViewById(R.id.add_driver);
        addTask = (Button) findViewById(R.id.add_task);
    }


}