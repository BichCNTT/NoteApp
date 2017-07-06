package com.example.asus.tutorialprogramming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonNew;
    Button buttonDestroy;
    Button buttonDelete;
    LinearLayout lL2ActivityMain;
    Intent intent;
    MyData myData;
    ListView listView;
    List<DataStructure> listNote = new ArrayList<DataStructure>();
    ListNoteAdapter adapter;
    private ArrayList<DataStructure> listremove = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ánh xạ
        buttonNew = (Button) findViewById(R.id.buttonNewActivityMain);
        buttonDestroy = (Button) findViewById(R.id.buttonDestroyActivityMain);
        buttonDelete = (Button) findViewById(R.id.buttonDeleteActivityMain);
        lL2ActivityMain = (LinearLayout) findViewById(R.id.lL2ActivityMain);
        listView = (ListView) findViewById(R.id.listView);
        buttonNew.setOnClickListener(this);
        buttonDestroy.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        myData = new MyData(this);
        List<DataStructure> list = myData.getAllNote();
        listNote.addAll(list);
        adapter = new ListNoteAdapter(this, listNote);
        listView.setAdapter(adapter);
        registerForContextMenu(this.listView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            DataStructure newnote = (DataStructure) data.getSerializableExtra("new_note");
            listNote.add(newnote);
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonNewActivityMain:
                intent = new Intent(MainActivity.this, FirstActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.buttonDeleteActivityMain:
                for (int i = 0; i < listNote.size(); i++) {
                    View view = listView.getChildAt(i);
                    CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
                    if (checkBox.isChecked() == true) {
                        listremove.add(listNote.get(i));
                    }
                }
                if (listremove.size() == 0) return;
                for (int i = 0; i < listremove.size(); i++) {
                    listNote.remove(listremove.get(i));
                    myData.deleteNote(listremove.get(i));
                }
                listremove.clear();
                adapter.notifyDataSetChanged();
                break;
            case R.id.buttonDestroyActivityMain:
                for (int i = 0; i < listNote.size(); i++) {
                    View view = listView.getChildAt(i);
                    CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
                    if (checkBox.isChecked() == true) {
                        checkBox.setChecked(false);
                    }
                }
                break;
            default:
                break;
        }
    }
}
