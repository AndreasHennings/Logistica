package com.example.kristine.logistica.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kristine.logistica.JavaClasses.Driver;
import com.example.kristine.logistica.R;


import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Sarah on 06.08.2016.
 */
public class DriverAdapter extends ArrayAdapter<Driver> {

    private ArrayList<Driver> arrayList;
    private Context context;

    public DriverAdapter(Context context, ArrayList<Driver> arrayList){
        super(context, R.layout.driver,arrayList);
        this.arrayList=arrayList;
        this.context=context;
    }



    public View getView(int position, View convertView, ViewGroup parent){
        View v;
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.driver,null);
        }
        else {

            v=convertView;
        }

        if(arrayList.get(position)!=null) {
            TextView id = (TextView) v.findViewById(R.id.test_id);
            TextView name = (TextView) v.findViewById(R.id.test_name);
            TextView x_pos=(TextView)v.findViewById(R.id.test_x);
            TextView y_pos=(TextView) v.findViewById(R.id.test_y);

            id.setText(""+arrayList.get(position).getId());
            name.setText(arrayList.get(position).getName());
            x_pos.setText("X: "+arrayList.get(position).getX_pos());
            y_pos.setText("Y: "+arrayList.get(position).getY_pos());

        }

        return v;
    }
}
