package com.project.doctorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class app1page extends AppCompatActivity {

    ImageView doctor_btn,patient_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app1page);

        doctor_btn = (ImageView) findViewById(R.id.docbtn);
        patient_btn = (ImageView)findViewById(R.id.patientbtn);


        doctor_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent docintent = new Intent(app1page.this,signDoctor.class);
                startActivity(docintent);
            }
        });

        patient_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent patientintent = new Intent(app1page.this,signPatient.class);
                startActivity(patientintent);
            }
        });


    }
}