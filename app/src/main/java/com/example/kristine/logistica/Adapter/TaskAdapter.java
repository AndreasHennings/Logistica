package com.example.kristine.logistica.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kristine.logistica.JavaClasses.Driver;
import com.example.kristine.logistica.JavaClasses.Task;
import com.example.kristine.logistica.R;

import java.util.ArrayList;

/**
 * Created by Sarah on 06.08.2016.
 */
public class TaskAdapter extends ArrayAdapter<Task> {

    private Context context;
    private ArrayList<Task> arrayList;

    public TaskAdapter(Context context, ArrayList<Task> arrayList){
        super(context, R.layout.task,arrayList);

        this.context=context;
        this.arrayList=arrayList;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View v= convertView;
        if(v==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.task,null);
        }

        Task task = arrayList.get(position);
        if(task!=null){
            TextView id= (TextView)v.findViewById(R.id.test_id_task);
            TextView date=(TextView)v.findViewById(R.id.test_date);
            TextView time=(TextView)v.findViewById(R.id.test_time) ;
            TextView sourceAddressX =(TextView)v.findViewById(R.id.test_source_x);
            TextView sourceAddressY =(TextView)v.findViewById(R.id.test_source_y);
            TextView targetAddressX=(TextView)v.findViewById(R.id.test_target_x);
            TextView targetAddressY=(TextView)v.findViewById(R.id.test_target_y);


            id.setText(""+task.getId());
            sourceAddressX.setText("Source X: "+task.getSource_x());
            sourceAddressY.setText("Source Y: "+task.getSource_y());
            date.setText(""+task.getDate());
            time.setText(""+task.getTime());
            targetAddressX.setText("Target X: "+task.getTarget_x());
            targetAddressY.setText("Target Y: "+task.getTarget_y());

            v.setBackgroundColor(getColor(task.getStatus()));


        }
        return v;
    }

    private int getColor(int status) {
        switch (status){
            case 0:
                return R.color.to_pick_up;
            case 1:
                return R.color.in_process;
            case 2:
                return R.color.delivered;
        }
        return 0;
    }
}
