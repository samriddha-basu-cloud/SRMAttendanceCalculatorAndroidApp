package com.example.srmattendancecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class outputScreen extends AppCompatActivity {


    private TextView ct1, ct2, ct3, sem, per;
    private String rcvct1, rcvct2, rcvct3,rcvsem, rcvper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_screen);

        ct1=findViewById(R.id.ct1);
        ct2=findViewById(R.id.ct2);
        ct3=findViewById(R.id.ct3);
        sem=findViewById(R.id.sem);
        per=findViewById(R.id.per);


        Intent intent= getIntent();

        rcvct1=intent.getStringExtra("CT1");
        rcvct2=intent.getStringExtra("CT2");
        rcvct3=intent.getStringExtra("CT3");
        rcvsem=intent.getStringExtra("SEM");
        rcvper=intent.getStringExtra("PER");

        ct1.setText(rcvct1);
        ct2.setText(rcvct2);
        ct3.setText(rcvct3);
        sem.setText(rcvsem);
        per.setText(rcvper);



    }
}