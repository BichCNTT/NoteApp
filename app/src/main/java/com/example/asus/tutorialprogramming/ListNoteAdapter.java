package com.example.asus.tutorialprogramming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Asus on 7/3/2017.
 */

public class ListNoteAdapter extends BaseAdapter {
    Context context;
    List<DataStructure> noteList;
    LayoutInflater inflater;
    public MyViewHolder myViewHolder;
    public ListNoteAdapter(Context context, List<DataStructure> noteList) {
        this.context = context;
        this.noteList = noteList;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return noteList.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row, null);
            myViewHolder = new MyViewHolder();
            convertView.setTag(myViewHolder);
        } else {
            //Luu lai du lieu khi keo len xuong
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        myViewHolder.textViewTitle = (TextView) convertView.findViewById(R.id.textViewTitle);
        myViewHolder.textViewDateTime = (TextView) convertView.findViewById(R.id.textViewDateTime);
        myViewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
        myViewHolder.textViewTitle.setText(noteList.get(position).getmTitle());
        myViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        myViewHolder.textViewDateTime.setText(noteList.get(position).getmTime());
        myViewHolder.checkBox.setChecked(noteList.get(position).getmcheckBox());
        return convertView;
    }

    public static class MyViewHolder {
        TextView textViewTitle;
        TextView textViewDateTime;
        CheckBox checkBox;
    }
}