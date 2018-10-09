package com.example.longnd.my_connection_sql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ControlStudent extends BaseAdapter {

    private Context context;
    private  int myLayout;
    private ArrayList<Student> array;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getMyLayout() {
        return myLayout;
    }

    public void setMyLayout(int myLayout) {
        this.myLayout = myLayout;
    }

    public ArrayList<Student> getArray() {
        return array;
    }

    public void setArray(ArrayList<Student> array) {
        this.array = array;
    }

    public ControlStudent(Context context, int myLayout, ArrayList<Student> array) {

        this.context = context;
        this.myLayout = myLayout;
        this.array = array;
    }


    // return count list
    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
       LayoutInflater lay = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=  lay.inflate(myLayout,null);

        //  gan gia tri
        TextView id = (TextView)view.findViewById(R.id.id);
        // truyen array -> text in layout
        id.setText(array.get(position).getId());
        TextView name= (TextView) view.findViewById(R.id.name);
        // truyen array - > text in layout
        name.setText(array.get(position).getName());
        return  view;
    }
}
