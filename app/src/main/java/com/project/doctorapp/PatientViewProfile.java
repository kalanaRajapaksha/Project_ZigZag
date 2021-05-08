package com.project.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class PatientViewProfile extends AppCompatActivity {

    TextView Name,ContactNo,Email,DOB,Address,Gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view_profile);

        Name = findViewById(R.id.patientName);
        ContactNo=findViewById(R.id.patientPhone);
        Email = findViewById(R.id.patientEmail);
        DOB = findViewById(R.id.patientDOB);
        Address = findViewById(R.id.patientAddress);
        Gender = findViewById(R.id.patientGender);

        Intent profileIntent = getIntent();

        String PatientName = profileIntent.getStringExtra("_patientName");
        String PatientContactNo = profileIntent.getStringExtra("_patientcontactNo");
        String PatientEmail = profileIntent.getStringExtra("_patientemail");
        String PatientDOB = profileIntent.getStringExtra("_patientdob");
        String PatientAddress = profileIntent.getStringExtra("_patientaddress");
        String PatientGender = profileIntent.getStringExtra("_patientgender");

        Name.setText(PatientName);
        ContactNo.setText(PatientContactNo);
        Email.setText(PatientEmail);
        DOB.setText(PatientDOB);
        Address.setText(PatientAddress);
        Gender.setText(PatientGender);


    }
}