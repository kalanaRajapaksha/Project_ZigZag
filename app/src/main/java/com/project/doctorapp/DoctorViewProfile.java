package com.project.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class DoctorViewProfile extends AppCompatActivity {

    TextView Name,ContactNo,Email,DOB,Address,Gender;
    Button edit,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view_profile);
        edit = findViewById(R.id.docProfileEdit);
        delete = findViewById(R.id.docProfileDelete);


        Name = findViewById(R.id.docName);
        ContactNo=findViewById(R.id.docPhone);
        Email = findViewById(R.id.docEmail);
        DOB = findViewById(R.id.docDOB);
        Address = findViewById(R.id.docAddress);
        Gender = findViewById(R.id.docGender);

        Intent profileIntent = getIntent();

        String DoctorName = profileIntent.getStringExtra("_docName");
        String DoctorContactNo = profileIntent.getStringExtra("_contactNo");
        String DoctorEmail = profileIntent.getStringExtra("_email");
        String DoctorDOB = profileIntent.getStringExtra("_dob");
        String DoctorAddress = profileIntent.getStringExtra("_address");
        String DoctorGender = profileIntent.getStringExtra("_gender");

        Name.setText(DoctorName);
        ContactNo.setText(DoctorContactNo);
        Email.setText(DoctorEmail);
        DOB.setText(DoctorDOB);
        Address.setText(DoctorAddress);
        Gender.setText(DoctorGender);



        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editintent = new Intent(DoctorViewProfile.this,doctorEditProfile.class);
                editintent.putExtra("_doctorName",DoctorName);
                startActivity(editintent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deleteintent = new Intent(DoctorViewProfile.this,doctorEditProfile.class);
                startActivity(deleteintent);
            }
        });
    }

    public void vieHome(View view){
        Intent in = new Intent(DoctorViewProfile.this, app1page.class);
        startActivity(in);
    }
}