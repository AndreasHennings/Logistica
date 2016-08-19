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

public class AddDriverActivity extends AppCompatActivity {

    MyDatabaseAdapter db;

    EditText getName;
    EditText getX;
    EditText getY;
    Button enter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver);
        setActionBarInActivity();
        initComponents();
        setClickListener();
    }

    private void setActionBarInActivity() {
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
    }

    private void setClickListener() {
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = getName.getText().toString();

                int xPos = Integer.parseInt(getX.getText().toString());
                int yPos = Integer.parseInt(getY.getText().toString());
                if(getX.getText().toString().length()==0|| getX.getText().toString().length()==0 || getY.getText().toString().length()==0){
                    Toast.makeText(AddDriverActivity.this,"Input is incorrect!", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.addDriver(name, xPos,yPos);
                    Toast.makeText(AddDriverActivity.this, "Input was added.", Toast.LENGTH_SHORT).show();
                }
                getName.setText("");
                getX.setText("");
                getY.setText("");

            }
        });
    }

    private void initComponents() {
        db = new MyDatabaseAdapter(this);

        getName = (EditText) findViewById(R.id.enter_driver_name);
        getX = (EditText) findViewById(R.id.initial_driver_x_pos);
        getY = (EditText) findViewById(R.id.initial_driver_y_pos);
        enter = (Button) findViewById(R.id.enter_driver_button);
    }

}
