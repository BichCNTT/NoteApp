package com.example.asus.tutorialprogramming;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class FirstActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextWorkTitle;
    EditText editTextWorkData;
    TextView textViewDateTime;
    Button buttonAdd;
    Button buttonDestroyFirst;
    DatePicker datePicker;
    SQLiteDatabase db;
    MyData myData;
    public DataStructure note = new DataStructure();
    @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        editTextWorkTitle = (EditText) findViewById(R.id.editTextTitleWorkActivityFirst);
        editTextWorkData = (EditText) findViewById(R.id.editTextDataWorkActivityFirst);
        textViewDateTime = (TextView) findViewById(R.id.textViewDateTime);
        datePicker = (DatePicker) findViewById(R.id.dateTimePickerActivityFirst);
        buttonAdd = (Button) findViewById(R.id.buttonAddActivityFirst);
        buttonDestroyFirst = (Button) findViewById(R.id.buttonDestroyActivityFirst);
        datePicker.setVisibility(View.GONE);
        buttonDestroyFirst.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
        Context context = FirstActivity.this;
        myData = new MyData(context);
        textViewDateTime.setText(datePicker.getDayOfMonth() + "/" + datePicker.getMonth() + "/" + datePicker.getYear());
        textViewDateTime.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    datePicker.setVisibility(View.VISIBLE);
                                                }
                                            }
        );
    }

    public void onClick(View v) {

        switch (v.getId()) {
            //Hủy
            case R.id.buttonDestroyActivityFirst:
                showDialog();
                break;
            //Thêm
            case R.id.buttonAddActivityFirst:
                textViewDateTime.setText(datePicker.getDayOfMonth() + "/" + datePicker.getMonth() + "/" + datePicker.getYear());
                note.setmTitle(editTextWorkTitle.getText().toString());
                note.setmData(editTextWorkData.getText().toString());
                note.setmTime(textViewDateTime.getText().toString());
                note.setmcheckBox(false);
                myData.insertNote(note);
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("new_note", note);
                setResult(1,intent);
                finish();
                break;
            default:
                break;
        }
    }

    public void showDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Có hủy không?");
        alert.setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editTextWorkTitle.setText("");
                editTextWorkData.setText("");
                textViewDateTime.setText("");
            }
        });
        alert.setPositiveButton("Từ chối", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
}
