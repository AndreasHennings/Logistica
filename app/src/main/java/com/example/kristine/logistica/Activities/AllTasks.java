package com.example.kristine.logistica.Activities;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kristine.logistica.Adapter.TaskAdapter;
import com.example.kristine.logistica.Database.MyDatabaseAdapter;
import com.example.kristine.logistica.JavaClasses.Task;
import com.example.kristine.logistica.R;

import java.util.ArrayList;

public class AllTasks extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Task> arrayList=new ArrayList<>();
    private MyDatabaseAdapter db;
    private TaskAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);
        setActionBarInActivity();
        initDB();
        initUI();
        updateList();

    }

    private void updateList() {
        arrayList.clear();
        arrayList.addAll(db.getAllTask());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void initUI() {
        initListView();
        initListAdapter();
    }



    private void initListAdapter() {
        adapter=new TaskAdapter(this,arrayList);

    }

    private void initListView() {

        listView=(ListView)findViewById(R.id.listView_all_tasks);
    }

    private void initDB() {
        db=new MyDatabaseAdapter(this);
    }

    private void setActionBarInActivity() {
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id= item.getItemId();
        switch (id){
            case R.id.plus_driver:
                Intent intent = new Intent(AllTasks.this,AddTaskActivity.class);
                startActivity(intent);
                break;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        updateList();

    }


}
