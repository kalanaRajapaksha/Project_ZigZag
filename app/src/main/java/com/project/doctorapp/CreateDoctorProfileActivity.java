package com.project.doctorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateDoctorProfileActivity extends AppCompatActivity {

    EditText docName, Gender, ContactNo, email, DOB, Address, Password;
    Button create;
    TextView loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_doctor_profile);

        docName = findViewById(R.id.docCreateName);
        Gender = findViewById(R.id.docCreateGender);
        ContactNo = findViewById(R.id.docCreatePhone);
        email = findViewById(R.id.docCreateEmail);
        DOB = findViewById(R.id.docCreateBirth);
        Address = findViewById(R.id.docCreateAddress);
        Password = findViewById(R.id.docCreatePass);
        create = findViewById(R.id.create_btn);
        loginbtn = findViewById(R.id.loginbtn);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String docN = docName.getText().toString().trim();
                String ContNo = ContactNo.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String dob = DOB.getText().toString().trim();
                String address = Address.getText().toString().trim();
                String gender = Gender.getText().toString().trim();
                String password = Password.getText().toString().trim();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("Doctor");

                Doctor doctor = new Doctor(docN,ContNo, gender, dob, address, password,Email);
                ref.child(docN).setValue(doctor);

                docName.setText("");
                Gender.setText("");
                ContactNo.setText("");
                email.setText("");
                DOB.setText("");
                Address.setText("");
                Password.setText("");
                Toast.makeText(CreateDoctorProfileActivity.this, "Doctor Profile Created!", Toast.LENGTH_LONG).show();
            }
        });

    }
}