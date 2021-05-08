package com.project.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreatePatientProfileActivity extends AppCompatActivity {

    EditText patientName, Address, ContactNo, DOB, email, Password, Gender;
    Button createpatient;
    TextView loginbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient_profile);

        patientName = findViewById(R.id.patientName);
        Gender = findViewById(R.id.patientGender);
        ContactNo = findViewById(R.id.patientNumber);
        email = findViewById(R.id.patientEmail);
        DOB = findViewById(R.id.patientDOB);
        Address = findViewById(R.id.patientAddress);
        Password = findViewById(R.id.patientPassword);
        createpatient = findViewById(R.id.patientProfileCreate);

        createpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patientN = patientName.getText().toString().trim();
                String ContNo = ContactNo.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String dob = DOB.getText().toString().trim();
                String address = Address.getText().toString().trim();
                String gender = Gender.getText().toString().trim();
                String password = Password.getText().toString().trim();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("Patient");

                Patient patient = new Patient(patientN,ContNo, gender, dob, address, password,Email);
                ref.child(patientN).setValue(patient);

                patientName.setText("");
                Gender.setText("");
                ContactNo.setText("");
                email.setText("");
                DOB.setText("");
                Address.setText("");
                Password.setText("");
                Toast.makeText(CreatePatientProfileActivity.this, "Patient Profile Created!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void vieHome(View view){
        Intent in = new Intent(CreatePatientProfileActivity.this, app1page.class);
        startActivity(in);
    }

    public void profile(View view){
        Intent in = new Intent(CreatePatientProfileActivity.this, PatientViewProfile.class);
        startActivity(in);
    }
}