package com.example.srmattendancecalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class output extends AppCompatActivity {

    private String selectedSubject;
    private TextView tvSubjectSpinner;
    private Spinner spinnersubject;
    private ArrayAdapter<CharSequence> adaptersubject;

    TextView receiver_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

//        spinnersubject = findViewById(R.id.spinnersubject);
//
//        adaptersubject=ArrayAdapter.createFromResource(this, R.array.array_subject, R.layout.item_file);
//
//        adaptersubject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinnersubject.setAdapter(adaptersubject);


        // create the get Intent object
        Intent intent = getIntent();
        // receive the value by getStringExtra() method and
        // key must be same which is send by first activity
        String str = intent.getStringExtra("message_key");
        // display the string into textView
        receiver_msg.setText(str);



    }
}