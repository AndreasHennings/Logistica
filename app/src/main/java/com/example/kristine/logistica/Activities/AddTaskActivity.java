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
import android.widget.EditText;
import android.widget.Toast;

import com.example.kristine.logistica.Database.MyDatabaseAdapter;
//import com.example.kristine.logistica.JavaClasses.Position;
import com.example.kristine.logistica.R;

import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity {


    private EditText sourceX;
    private EditText sourceY;
    private EditText targetX;
    private EditText targetY;

    private Button button;

    private MyDatabaseAdapter db;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        calendar=Calendar.getInstance();
        setActionBarInActivity();
        initUI();
        setClickListener();
    }
    private void setActionBarInActivity() {
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
    }

    private void setClickListener() {
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int sXPos = Integer.parseInt(sourceX.getText().toString());
                int sYPos = Integer.parseInt(sourceY.getText().toString());
                int tXPos = Integer.parseInt(targetX.getText().toString());
                int tYPos = Integer.parseInt(targetY.getText().toString());
                String d= calendar.get(Calendar.DAY_OF_MONTH)+"."+calendar.get(Calendar.MONTH)+"."+calendar.get(Calendar.YEAR);
                String t= calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);

                if (sourceX.getText() != null && sourceY.getText() != null && targetX.getText() != null && targetY.getText() != null) {
                    db.addTask(sXPos,sYPos,tXPos,tYPos, d,t);
                    Toast.makeText(AddTaskActivity.this, "Task was added.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddTaskActivity.this, "Input is incorrect!", Toast.LENGTH_SHORT).show();
                }
                sourceX.setText("");
                sourceY.setText("");
                targetX.setText("");
                targetY.setText("");

            }

        });

    }

    private void initUI() {
        sourceX=(EditText)findViewById(R.id.source_x_pos);
        sourceY=(EditText)findViewById(R.id.source_y_pos);
        targetX=(EditText)findViewById(R.id.delivery_x_pos);
        targetY=(EditText)findViewById(R.id.delivery_y_pos);
        button=(Button)findViewById(R.id.enter_task_button);


        db=new MyDatabaseAdapter(this);
    }


}
