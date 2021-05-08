package com.project.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditClinicActivity extends AppCompatActivity {
    EditText cName,doctName,hosptName,descpt,dtTime;
    Button upBtn;
    String id,clinicName,docName,hospName,desc,dateTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_clinic);

        cName = findViewById(R.id.cCname);
        doctName =  findViewById(R.id.cDname);
        hosptName= findViewById(R.id.cHosName);
        descpt = findViewById(R.id.cDesc);
        dtTime = findViewById(R.id.cDate);
        upBtn = findViewById(R.id.saveBtn);


        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        clinicName = intent.getStringExtra("clinicName");
        docName = intent.getStringExtra("docName");
        hospName = intent.getStringExtra("hospName");
        desc = intent.getStringExtra("desc");
        dateTime = intent.getStringExtra("dateTime");

        cName.setText(clinicName);
        doctName.setText(docName);
        hosptName.setText(hospName);
        descpt.setText(desc);
        dtTime.setText(dateTime);


        upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Clinic Data").child(id);
                String clinicName,docName,hospName,desc,dateTime;
                clinicName = cName.getText().toString();
                docName = doctName.getText().toString();
                hospName = hosptName.getText().toString();
                desc = descpt.getText().toString();
                dateTime = dtTime.getText().toString();
                com.project.doctorapp.Clinic clinic1 = new com.project.doctorapp.Clinic(id,clinicName,docName,hospName,desc,dateTime);
                databaseReference.setValue(clinic1);
                Toast.makeText(com.project.doctorapp.EditClinicActivity.this,"Updated",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(com.project.doctorapp.EditClinicActivity.this, com.project.doctorapp.Doc_dashboard.class);
                startActivity(i);
            }
        });
    }
}