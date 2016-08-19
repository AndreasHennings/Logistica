package com.example.kristine.logistica.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kristine.logistica.JavaClasses.Driver;
//import com.example.kristine.logistica.JavaClasses.Position;
import com.example.kristine.logistica.JavaClasses.Task;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Kristine on 06.08.2016.
 */
public class MyDatabaseAdapter
{
    private MyDatabaseHelper helper;
    private SQLiteDatabase db;

    public static final String DRIVER_TABLE = "driver";
    public static final String TASK_TABLE = "task";
    public static final int DB_VERSION = 1;
    public static final String DB_LOGISTICA = "logistica";

    public static final String DRIVER_ID = "driver_id";
    public static final String DRIVER_NAME = "driver_name";
    public static final String DRIVER_POSITION_X = "driver_position_x";
    public static final String DRIVER_POSITION_Y = "driver_position_y";

    public static final String TASK_ID = "task_id";
    public static final String SOURCE_X = "source_x";
    public static final String SOURCE_Y = "source_y";
    public static final String TARGET_X = "target_x";
    public static final String TARGET_Y = "target_y";
    public static final String DATE = "date";
    public static final String TIME = "time";
    public static final String STATE = "state";


    public MyDatabaseAdapter(Context context)
    {
        helper = new MyDatabaseHelper(context, DB_LOGISTICA, null, DB_VERSION);
    }

    private void open()
    {
        try
        {
            db = helper.getWritableDatabase();
        } catch (SQLException e)
        {
            db = helper.getReadableDatabase();
        }
    }

    private void close()
    {
        db.close();
    }


    public void addDriver(String name, int x, int y)
    {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DRIVER_NAME, name);
        contentValues.put(DRIVER_POSITION_X, x);
        contentValues.put(DRIVER_POSITION_Y, y);
        db.insert(DRIVER_TABLE, null, contentValues);
        close();

    }


    public boolean addTask(int source_x, int source_y, int target_x, int target_y, String date, String time)
    {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TARGET_X, source_x);
        contentValues.put(TARGET_Y, source_y);
        contentValues.put(SOURCE_X, target_x);
        contentValues.put(SOURCE_Y, target_y);
        contentValues.put(STATE, 0);
        contentValues.put(DATE, date);
        contentValues.put(TIME, time);
        db.insert(TASK_TABLE, null, contentValues);
        close();

        return true;
    }


    public ArrayList<Driver> getAllDriver()
    {
        open();
        ArrayList<Driver> drivers = new ArrayList<>();
        Cursor cursor = db.query(DRIVER_TABLE, null, null, null, null, null, null);
        if (cursor.moveToFirst())
        {
            do
            {
                Driver driver = new Driver(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3));
                drivers.add(driver);
            }
            while (cursor.moveToNext());
        }
        close();
        return drivers;
    }


    public ArrayList<Task> getAllTask()
    {
        open();
        ArrayList<Task> tasks = new ArrayList<>();

        Cursor cursor = db.query(TASK_TABLE, null, null, null, null, null, null);
        if (cursor.moveToFirst())
        {
            do
            {
                Task task = new Task(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5), cursor.getString(6));
                tasks.add(task);
            } while (cursor.moveToNext());
        }
        close();
        return tasks;
    }


    public void updateDriver(Driver driver)
    {
        String update = driver.getId() + "=?";
        ContentValues contentValues = new ContentValues();
        contentValues.put(DRIVER_ID, driver.getId());
        contentValues.put(DRIVER_NAME, driver.getName());
        contentValues.put(DRIVER_POSITION_X, driver.getX_pos());
        contentValues.put(DRIVER_POSITION_Y, driver.getY_pos());
        open();
        db.update(DRIVER_TABLE, contentValues, update, new String[]{String.valueOf(driver.getId())});
        close();
    }

    public void updateTask(Task task)
    {
        String update = task.getId() + "=?";
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASK_ID, task.getId());
        contentValues.put(SOURCE_X, task.getSource_x());
        contentValues.put(SOURCE_Y, task.getSource_y());
        contentValues.put(TARGET_X, task.getTarget_x());
        contentValues.put(TARGET_Y, task.getTarget_y());
        contentValues.put(DATE, task.getDate());
        contentValues.put(TIME, task.getTime());
        contentValues.put(STATE, task.getStatus());
        open();
        db.update(TASK_TABLE, contentValues, update, new String[]{String.valueOf(task.getId())});
        close();
    }

    public Task getNearestTask(Driver driver)
    {
        int driverX = driver.getX_pos();
        int driverY = driver.getY_pos();
        ArrayList<Task> allTasks = getAllTask();
        Task nearestTask = null;
        int minDist = 10000;

        for (int i = 0; i < allTasks.size(); i++)
        {

            if (allTasks.get(i).getStatus() == 0)
            {

                int xDistance = Math.abs(driverX - allTasks.get(i).getSource_x());
                int yDistance = Math.abs(driverY - allTasks.get(i).getSource_y());
                int sumDistance = xDistance + yDistance;

                if (sumDistance < minDist)
                {
                    minDist = sumDistance;
                    nearestTask = allTasks.get(i);
                }
            }
        }
        return nearestTask;
    }

    //code für das überprüfen der id bei der registrierung des drivers

    public boolean idInDatabase(int id)
    {
        ArrayList<Driver> allDrivers = getAllDriver();
        for (int i = 0; i < allDrivers.size(); i++)
        {
            int currentId = allDrivers.get(i).getId();
            if (currentId == id)
            {
                return true;
            }
        }
        return false;
    }


    private class MyDatabaseHelper extends SQLiteOpenHelper
    {

        public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
        {
            super(context, name, factory, version);
        }

        private static final String CREATE_DRIVER_TABLE = "create table " + DRIVER_TABLE + " ("
                + DRIVER_ID + " integer primary key autoincrement, "
                + DRIVER_NAME + " text not null, "
                + DRIVER_POSITION_X + " integer, "
                + DRIVER_POSITION_Y + " integer);";


        private static final String CREATE_TASK_TABLE = "create table " + TASK_TABLE + " ("
                + TASK_ID + " integer primary key autoincrement, "
                + SOURCE_X + " integer, "
                + SOURCE_Y + " integer, "
                + TARGET_X + " integer, "
                + TARGET_Y + " integer, "
                + DATE + " string, "
                + TIME + " string, "
                + STATE + " integer, "
                + DRIVER_ID + " integer);";

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(CREATE_TASK_TABLE);
            db.execSQL(CREATE_DRIVER_TABLE);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {

        }
    }
}
